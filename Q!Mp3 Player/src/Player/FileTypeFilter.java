/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Junior
 */
public class FileTypeFilter extends FileFilter {

    private final String extensao;
    private final String descricao;
    
    //Construtor
    public FileTypeFilter(String extensao, String descricao){
        this.extensao = extensao;
        this.descricao = descricao;
    }
    
    @Override
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        }
        return file.getName().endsWith(extensao);
    }

    @Override
    public String getDescription() {
        return descricao + String.format(" (*%S)", extensao);
    }
    
}
