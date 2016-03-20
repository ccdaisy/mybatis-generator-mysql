package xyz.ccdaisy.utils;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author daisy
 *
 */
public class InsertSelectKeyPlugin extends PluginAdapter
{
    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        XmlElement selectKey = new XmlElement("selectKey");
        selectKey.addAttribute(new Attribute("keyProperty", "iID"));
        selectKey.addAttribute(new Attribute("resultType", "int"));
        selectKey.addAttribute(new Attribute("order", "AFTER"));
        selectKey.addElement(new TextElement("select LAST_INSERT_ID()"));
        element.addElement(selectKey);
        return super.sqlMapInsertElementGenerated(element, introspectedTable);
    };

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable)
    {
        // <selectKey keyProperty="iID" resultType="int" order="AFTER">
        //
        // select LAST_INSERT_ID()
        //
        // </selectKey>
        XmlElement selectKey = new XmlElement("selectKey");
        selectKey.addAttribute(new Attribute("keyProperty", "iID"));
        selectKey.addAttribute(new Attribute("resultType", "int"));
        selectKey.addAttribute(new Attribute("order", "AFTER"));
        selectKey.addElement(new TextElement("select LAST_INSERT_ID()"));
        element.addElement(selectKey);
        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    };

    public boolean validate(List<String> warnings)
    {
        return true;
    }
}
