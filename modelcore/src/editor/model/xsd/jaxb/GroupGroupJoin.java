//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.14 at 11:02:56 PM IST 
//


package editor.model.xsd.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupGroupJoin complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupGroupJoin">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.diagrams.editor.org/schema}BaseJoin">
 *       &lt;attribute name="parentEntitisColumnGroup" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="targetEntitisColumnGroup" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupGroupJoin")
public class GroupGroupJoin
    extends BaseJoin
{

    @XmlAttribute(name = "parentEntitisColumnGroup", required = true)
    protected String parentEntitisColumnGroup;
    @XmlAttribute(name = "targetEntitisColumnGroup", required = true)
    protected String targetEntitisColumnGroup;

    /**
     * Gets the value of the parentEntitisColumnGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentEntitisColumnGroup() {
        return parentEntitisColumnGroup;
    }

    /**
     * Sets the value of the parentEntitisColumnGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentEntitisColumnGroup(String value) {
        this.parentEntitisColumnGroup = value;
    }

    /**
     * Gets the value of the targetEntitisColumnGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetEntitisColumnGroup() {
        return targetEntitisColumnGroup;
    }

    /**
     * Sets the value of the targetEntitisColumnGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetEntitisColumnGroup(String value) {
        this.targetEntitisColumnGroup = value;
    }

}
