//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.26 at 09:30:30 PM IST 
//


package editor.model.xsd.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ColumnTitleJoin complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ColumnTitleJoin">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.diagrams.editor.org/schema}BaseJoin">
 *       &lt;attribute name="parentEntitisColumn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColumnTitleJoin")
public class ColumnTitleJoin
    extends BaseJoin
{

    @XmlAttribute(name = "parentEntitisColumn", required = true)
    protected String parentEntitisColumn;

    /**
     * Gets the value of the parentEntitisColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentEntitisColumn() {
        return parentEntitisColumn;
    }

    /**
     * Sets the value of the parentEntitisColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentEntitisColumn(String value) {
        this.parentEntitisColumn = value;
    }

}
