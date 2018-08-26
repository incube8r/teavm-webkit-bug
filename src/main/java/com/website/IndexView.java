package com.website;

import com.website.models.HomePageRecord;
import com.website.models.HomePageRecords;
import org.teavm.flavour.json.JSON;
import org.teavm.flavour.json.tree.Node;
import org.teavm.flavour.templates.BindTemplate;
import org.teavm.flavour.templates.Templates;
import org.teavm.flavour.widgets.BackgroundWorker;
import org.teavm.jso.ajax.XMLHttpRequest;
import org.teavm.jso.browser.Window;

@BindTemplate("templates/index.html")
public class IndexView {

    private String title = "";
    private String caption = "";
    private String description= "";
    private BackgroundWorker background = new BackgroundWorker();

    public IndexView() {
        load();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void load() {
        background.run(() -> {
            XMLHttpRequest xhr = XMLHttpRequest.create();
            xhr.open("GET", "e");
            xhr.setRequestHeader("Authorization", "");
            xhr.onComplete(new Runnable() {
                @Override
                public void run() {
                    String responseText = xhr.getResponseText();
                    try {
                        HomePageRecords records = JSON.deserialize(Node.parse(responseText), HomePageRecords.class);
                        setTitle(getRecordContent("Header", records));
                        setCaption(getRecordContent("HeaderCaption", records));
                        setDescription(getRecordContent("HeaderDescription", records));
                        Templates.update();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Window.alert(e.getMessage());
                    }
                }
            });
            xhr.send();
        });
    }

    private HomePageRecord getRecord(String name, HomePageRecords records) {
        for(HomePageRecord record : records.records) {
            if(record.fields.Name != null && record.fields.Name.equals(name)) {
                return record;
            }
        }
        return null;
    }

    private String getRecordContent(String name, HomePageRecords records) {
        for(HomePageRecord record : records.records) {
            if(record.fields.Name != null && record.fields.Name.equals(name)) {
                return record.fields.Content;
            }
        }
        return null;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
