/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notebook;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 *
 * @author kaa
 */
public class FXMLNoteBookController implements Initializable {
    
    @FXML
    private TextArea t;
    @FXML
    private ListView<String> list;
    @FXML
    private Button bcreate,bdelete,bsave;
    
    private final String separator;
    
    private String path;
    
    private int indikator;
    
    private final Toast toast;
    private final Language language;

    public FXMLNoteBookController() {
        this.toast = new Toast();
        this.language = new Language();
        this.separator = System.getProperty("file.separator");
        this.indikator=0;
    }
    @FXML
    private void createNoteAction() throws IOException {
        String item=dateAndTime();
        if(isWindows()){
            item=item.replace(":","-");
        }
        File f=new File(path+separator+item);
        if(f.createNewFile()){
             showNotes();
        }
        list.scrollTo(item);
        list.getSelectionModel().select(item);
        t.clear();
    }
    @FXML
    private void saveNoteAction(){
        String l=list.getSelectionModel().getSelectedItem();
        if(l==null){
            alertWindow(language.messageSave(1));
            if(!isEmpty(t.getText())){
            StringSelection ss = new StringSelection(t.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            toast.setMessage(language.messageSave(2));
            }
            return;
        }
        if(isEmpty(t.getText())){
            toast.setMessage(language.messageSave(3));
            return;
        }
          writer(path + separator + l , t.getText());
          if(indikator==0){
              toast.setMessage(language.messageSave(4));
          }else{
              toast.setMessage(language.messageSave(5));
              indikator=0;
          }
    }
    @FXML
    private void deleteNoteAction() throws IOException {
        File f=new File(path+separator+list.getSelectionModel().getSelectedItem());
        if(!f.exists()){
            return;
        }
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(500,140);
        alert.setTitle(language.messageDelete(1));
        alert.setHeaderText("");
        alert.setContentText(language.messageDelete(2)+" "+f.getName()+" ?");
        final Optional<ButtonType> resultAlert = alert.showAndWait();
        if (resultAlert.get() == ButtonType.OK) {
            if(f.delete()){
           showNotes();
           t.clear();
        }else{
           toast.setMessage(language.messageDelete(3));
        }
      }
    }
    @FXML
    private void listItemAction(){
         this.t.setText(this.reader(path+separator+this.list.getSelectionModel().getSelectedItem()));
    }
    public void exit(){
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(500,140);
        alert.setTitle(language.forExitWindow(1));
        alert.setHeaderText(language.forExitWindow(2));
        alert.setContentText(language.forExitWindow(3));
        final Optional<ButtonType> resultAlert = alert.showAndWait();
        if (resultAlert.get() == ButtonType.OK) {
             System.exit(0);
        }
    }
    private void alertWindow(final String s) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(400, 140);
        alert.setTitle(language.forAlertWindow());
        alert.setHeaderText("");
        alert.setContentText(s);
        alert.showAndWait();
    }
    private boolean isEmpty(String s){
        return s == null || s.trim().length() == 0;
    }
    private String dateAndTime() {
        final Calendar calendar = new GregorianCalendar();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy    hh:mm:ss");
        String dayName = "";
        switch (calendar.get(7)) {
            case 1:
                dayName = language.forDateAndTime(1);
                break;
            case 2:
                dayName = language.forDateAndTime(2);
                break;
            case 3:
                dayName = language.forDateAndTime(3);
                break;
            case 4:
                dayName = language.forDateAndTime(4);
                break;
            case 5:
                dayName = language.forDateAndTime(5);
                break;
            case 6:
                dayName = language.forDateAndTime(6);
                break;
            case 7:
                dayName = language.forDateAndTime(7);
                break;
        }
        return dayName + " " + sdf.format(calendar.getTime());
    }
    private void showNotes(){
        File files=new File(path);
        File[] f=files.listFiles();
        String[] notes=new String[f.length];
        for(int i=0;i<notes.length;i++){
        notes[i]=f[i].getName();
    }
        list.setItems(FXCollections.observableArrayList(notes).sorted());
    }
    private String reader(final String s) {
        StringBuilder f=new StringBuilder();
        try {
            final File file = new File(s);
            final BufferedReader br;
            try (FileReader fr = new FileReader(file)) {
                br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    f.append(str).append("\n");
                }
                fr.close();
            }
            br.close();
        }
        catch (IOException e) {
            e.getMessage();
        }
        return f.toString();
    }
    private void dirCreator(final String fPath) {
        final File file = new File(fPath);
        if (!file.exists()) {
            file.mkdir();
            if(file.exists()){
                alertWindow(language.forDirectoryCreator(1)+fPath);
            }else{
                alertWindow(language.forDirectoryCreator(2)+fPath);
                System.exit(0);
            }
        }
    }
    private boolean isWindows() {
final String os = System.getProperty("os.name").toLowerCase();
return os.contains("win");
}
    private boolean permissionRead(File file){
          if(!file.canRead()){
                    file.setReadable(true);
                    if(!file.canRead()){
                        return true;
                    }
                }
        return false;
    }
    private boolean permissionWrite(File file){
          if(!file.canWrite()){
                    file.setWritable(true);
                    if(!file.canWrite()){
                        return true;
                    }
                }
          return false;
    }
    private void writer(String pathFile,String text){
          try (final FileWriter fw = new FileWriter(pathFile)) {
            fw.write(text);
            fw.close();
             } catch (IOException e) {
                   e.getMessage();
                   indikator=1;
             }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String parentPath=null;
        try {
            parentPath=URLDecoder.decode(new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }
        path=parentPath+separator+"MyNoteBook";
        this.dirCreator(this.path);
        File f=new File(path);
        if(permissionRead(f)||permissionWrite(f)){
            if(permissionRead(f)&&permissionWrite(f)){
                alertWindow(language.permission(1));
            }else if(permissionRead(f)){
                alertWindow(language.permission(2));
            }else{
                alertWindow(language.permission(3));
            }
            System.exit(0);
        }
        showNotes();
        bcreate.setText(language.forNewButton());
        bdelete.setText(language.forDeleteButton());
        bsave.setText(language.forSaveButton());
    }
}
    
