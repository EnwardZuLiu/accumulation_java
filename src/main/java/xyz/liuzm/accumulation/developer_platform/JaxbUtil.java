package xyz.liuzm.accumulation.developer_platform;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JaxbUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JaxbUtil.class);

    public static <T> T xmlToBean(InputStream xml, Class clazz) {
        try {
            return (T) JAXBContext.newInstance(clazz).createUnmarshaller().unmarshal(xml);
        } catch (JAXBException e) {
            throw new RuntimeException("xml to bean is error," + e.getMessage());
        }
    }

    public static String beanToXml(Object element) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            Marshaller marshaller = JAXBContext.newInstance(element.getClass()).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(element, os);
        } catch (JAXBException e) {
            throw new RuntimeException("bean to xml is error" + e.getMessage());
        }
        return new String(os.toByteArray(), Charsets.UTF_8);
    }

    public static void verification(InputStream xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        try {
            DocumentBuilder parser = dbf.newDocumentBuilder();

            Document document = parser.parse(xml);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Source schemaFile = new StreamSource(Resources.getResource("developer_platform.xsd").getPath());
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();


            validator.validate(new DOMSource(document));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new XmlVerificationException(e.getMessage());
        }
    }

    /**
     * 用来自定义验证 xml 是否合法
     *
     * @param xml
     */
    public static void customVerification(InputStream xml) {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true); // never forget this!
        Document doc = null;
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            doc = builder.parse(xml);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*";
        try {
            XPathExpression x = xpath.compile(expression);
            NodeList nodes = (NodeList) x.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                LOG.debug("{}",i);
                Node node = nodes.item(i);
                LOG.debug(node.getNodeName());
            }
        } catch (XPathExpressionException e) {
//            e.printStackTrace();
            throw new XmlVerificationException(e.getMessage());
        }
    }

    public static class XmlVerificationException extends RuntimeException {
        public XmlVerificationException(String message) {
            super(message);
        }
    }

}
