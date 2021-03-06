package com.guiwuu.jpractice.ood.container.ioc.guice;

import java.io.IOException;

import com.google.inject.Inject;
import com.guiwuu.jpractice.ood.ood.dip.Copier;
import com.guiwuu.jpractice.ood.ood.dip.Reader;
import com.guiwuu.jpractice.ood.ood.dip.Writer;

public class GuiceCopier extends Copier {

    @Inject
    private Reader reader;
    @Inject
    private Writer writer;

    public void copy() throws IOException {
        int ch;
        while ((ch = reader.read()) >= 0) {
            writer.write((char) ch);
        }
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}