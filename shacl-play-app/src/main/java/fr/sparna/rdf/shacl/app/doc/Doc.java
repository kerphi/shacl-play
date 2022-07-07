package fr.sparna.rdf.shacl.app.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sparna.rdf.shacl.app.CliCommandIfc;
import fr.sparna.rdf.shacl.app.InputModelReader;
import fr.sparna.rdf.shacl.doc.model.ShapesDocumentation;
import fr.sparna.rdf.shacl.doc.read.ShapesDocumentationModelReader;
import fr.sparna.rdf.shacl.doc.read.ShapesDocumentationReaderIfc;
import fr.sparna.rdf.shacl.doc.write.ShapesDocumentationJacksonXsltWriter;
import fr.sparna.rdf.shacl.doc.write.ShapesDocumentationWriterIfc;
import fr.sparna.rdf.shacl.doc.write.ShapesDocumentationXmlWriter;

public class Doc implements CliCommandIfc {

	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	@Override
	public void execute(Object args) throws Exception {
		ArgumentsDoc a = (ArgumentsDoc)args;
		
		//Call imagen
		
		
		// read input file or URL
		Model shapesModel = ModelFactory.createDefaultModel(); 
		InputModelReader.populateModel(shapesModel, a.getInput(), null);
		
		// read ontology file
		Model owlModel = ModelFactory.createDefaultModel(); 
		if(a.getOntologies() != null) {
			InputModelReader.populateModel(owlModel, a.getOntologies(), null);
		}
		
		// create output dir if not existing
		File outputDir = a.getOutput().getParentFile();
		if(outputDir != null && !outputDir.exists()) {
			outputDir.mkdirs();
		}
		
		String name_img = null;
		if(a.getImgLogo() != null) {
			
			if(new File(a.getImgLogo()).exists()) {
				File fileImg = new File(a.getImgLogo()); 
				File fileOut = new File(a.getOutput().toString());
				name_img = fileImg.getName();
				// copy imagen file in the output directory
				Path sourceImg = FileSystems.getDefault().getPath(a.getImgLogo().toString());
				Path outputDirImg = FileSystems.getDefault().getPath(fileOut.getParentFile().getPath()+"\\"+name_img);
				try {
					System.out.println(sourceImg);
					System.out.println(outputDirImg);
					Files.copy(sourceImg, outputDirImg, StandardCopyOption.REPLACE_EXISTING);
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}else {
				name_img = a.getImgLogo();
			}
		}
		
		// generate doc
		// true to read diagram
		ShapesDocumentationReaderIfc reader = new ShapesDocumentationModelReader(true,name_img);
		ShapesDocumentation doc = reader.readShapesDocumentation(
				shapesModel,
				owlModel,
				a.getLanguage(),
				a.getInput().get(0).getName(),
				// avoid arrows to empty boxes
				true
		);
		
		FileOutputStream out = new FileOutputStream(a.getOutput());
		if(a.getOutput().getName().endsWith(".xml")) {
			// 2. write Documentation structure to XML
			ShapesDocumentationWriterIfc writer = new ShapesDocumentationXmlWriter();
			writer.write(doc, a.getLanguage(), out);
		} else {
			// 2. write Documentation structure to HTML
			ShapesDocumentationWriterIfc writer = new ShapesDocumentationJacksonXsltWriter();
			writer.write(doc, a.getLanguage(), out);
		}
		out.close();
	
	}
	
}
