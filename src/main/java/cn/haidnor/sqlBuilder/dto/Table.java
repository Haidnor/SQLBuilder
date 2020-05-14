package cn.haidnor.sqlBuilder.dto;

import cn.haidnor.sqlBuilder.enums.Charset;
import cn.haidnor.sqlBuilder.enums.Engine;

import java.util.ArrayList;

/**
 * @author Haidnor
 */
public class Table {
    private String name = "";
    private ArrayList<Column> columns = new ArrayList<>();
    private Engine engine = Engine.InnoDB;
    private Charset charset = Charset.utf8;
    private String comments = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

}
