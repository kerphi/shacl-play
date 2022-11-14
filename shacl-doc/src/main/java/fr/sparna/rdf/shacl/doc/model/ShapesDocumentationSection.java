package fr.sparna.rdf.shacl.doc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(Include.NON_NULL)
public class ShapesDocumentationSection {

	private String title;
	private String uri;
	private String description;
	private String targetClassLabel;
	private String targetClassUri;
	private String pattern;
	private String nodeKind;
	private Boolean closed;
	private String skosExample;
	
	@JacksonXmlElementWrapper(localName="superClasses")
	@JacksonXmlProperty(localName = "link")
	private List<Link> superClasses;
	
	@JacksonXmlElementWrapper(localName="properties")
	@JacksonXmlProperty(localName = "property")
	public List<PropertyShapeDocumentation> propertySections;

	public List<Link> getSuperClasses() {
		return superClasses;
	}

	public void setSuperClasses(List<Link> superClasses) {
		this.superClasses = superClasses;
	}

	public String getSkosExample() {
		return skosExample;
	}

	public void setSkosExample(String skosExample) {
		this.skosExample = skosExample;
	}

	public String getTitle() {
		return title;		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTargetClassLabel() {
		return targetClassLabel;
	}

	public void setTargetClassLabel(String targetClassLabel) {
		this.targetClassLabel = targetClassLabel;
	}
	
	public String getTargetClassUri() {
		return targetClassUri;
	}

	public void setTargetClassUri(String targetClassUri) {
		this.targetClassUri = targetClassUri;
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getNodeKind() {
		return nodeKind;
	}

	public void setNodeKind(String nodeKind) {
		this.nodeKind = nodeKind;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	
	public List<PropertyShapeDocumentation> getPropertySections() {
		return propertySections;
	}
	public void setPropertySections(List<PropertyShapeDocumentation> propertySections) {
		this.propertySections = propertySections;
	}	
}
