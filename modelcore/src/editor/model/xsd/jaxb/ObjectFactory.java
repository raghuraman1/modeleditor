//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.26 at 09:30:30 PM IST 
//


package editor.model.xsd.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the editor.model.xsd.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Model_QNAME = new QName("http://www.diagrams.editor.org/schema", "model");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: editor.model.xsd.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link ColumnTitleJoin }
     * 
     */
    public ColumnTitleJoin createColumnTitleJoin() {
        return new ColumnTitleJoin();
    }

    /**
     * Create an instance of {@link BaseJoin }
     * 
     */
    public BaseJoin createBaseJoin() {
        return new BaseJoin();
    }

    /**
     * Create an instance of {@link Column }
     * 
     */
    public Column createColumn() {
        return new Column();
    }

    /**
     * Create an instance of {@link Coordinates }
     * 
     */
    public Coordinates createCoordinates() {
        return new Coordinates();
    }

    /**
     * Create an instance of {@link TitleColumnJoin }
     * 
     */
    public TitleColumnJoin createTitleColumnJoin() {
        return new TitleColumnJoin();
    }

    /**
     * Create an instance of {@link GroupGroupJoin }
     * 
     */
    public GroupGroupJoin createGroupGroupJoin() {
        return new GroupGroupJoin();
    }

    /**
     * Create an instance of {@link ColumnGroup }
     * 
     */
    public ColumnGroup createColumnGroup() {
        return new ColumnGroup();
    }

    /**
     * Create an instance of {@link ColumnColumnJoin }
     * 
     */
    public ColumnColumnJoin createColumnColumnJoin() {
        return new ColumnColumnJoin();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Model }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.diagrams.editor.org/schema", name = "model")
    public JAXBElement<Model> createModel(Model value) {
        return new JAXBElement<Model>(_Model_QNAME, Model.class, null, value);
    }

}
