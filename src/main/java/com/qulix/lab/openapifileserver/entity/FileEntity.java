package com.qulix.lab.openapifileserver.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class FileEntity {
    private String name;
    private String path; // relative path
    private Long length;
    private FileAttribute[] fileAttributes;
    private String[] innerFiles;

    public FileEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public FileAttribute[] getFileAttributes() {
        return fileAttributes;
    }

    public void setFileAttributes(FileAttribute[] fileAttributes) {
        this.fileAttributes = fileAttributes;
    }

    public String[] getInnerFiles() {
        return innerFiles;
    }

    public void setInnerFiles(String[] innerFiles) {
        this.innerFiles = innerFiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(path, that.path) && Objects.equals(length, that.length) && Arrays.equals(fileAttributes, that.fileAttributes) && Arrays.equals(innerFiles, that.innerFiles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, path, length);
        result = 31 * result + Arrays.hashCode(fileAttributes);
        result = 31 * result + Arrays.hashCode(innerFiles);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");

        stringJoiner.add("name = " + name);
        stringJoiner.add("path = " + path);
        stringJoiner.add("length = " + length);

        String attributes = "";
        for (FileAttribute attribute: fileAttributes) {
            attributes += attribute + ", ";
        }
        stringJoiner.add("fileAttributes = " + attributes);

        if (innerFiles != null) {
            String strInnerFiles = "";
            int numberLine = 1;
            for (String innerFile: innerFiles) {
                strInnerFiles += innerFile;
                if (numberLine % 10 == 0) {
                    strInnerFiles += "\n";
                } else {
                    strInnerFiles += ", ";
                }
                numberLine++;
            }
            stringJoiner.add("innerFiles = " + strInnerFiles);
        } else {
            stringJoiner.add("innerFiles = none");
        }


        return stringJoiner.toString();
    }

}
