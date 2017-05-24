package com.example.florian.exam_240517;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by florian on 24/05/2017.
 */

public class XML_Parsing extends AppCompatActivity {

    public ArrayList<Object_Classes.Contacts> list_contacts_xml;
    ListView Displayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_parsing);
        list_contacts_xml = new ArrayList<Object_Classes.Contacts>();
        Displayer = (ListView) findViewById(R.id.List_XML);


        try {
            InputStream is = getAssets().open("contacts.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("contact");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    Object_Classes.Contacts someguy = new Object_Classes.Contacts();

                    someguy.setId(getValue("id", element2));
                    someguy.setCName(getValue("name", element2));
                    someguy.setMail(getValue("email", element2));
                    someguy.setAdress(getValue("adresse", element2));
                    someguy.setGender(getValue("gender", element2));

                    list_contacts_xml.add(someguy);

                }
            }
            CustomAdapter adapter = new CustomAdapter(this, list_contacts_xml);
            Displayer.setAdapter(adapter);

        } catch (Exception e) {e.printStackTrace();}

    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}