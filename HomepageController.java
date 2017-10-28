/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import static javax.swing.text.html.HTML.Tag.S;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;
import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.S;



/**
 *
 * @author feiping
 */
public class HomepageController implements Initializable {
    
    // upload files and show files list. 
    private Label label;
    @FXML
    private JFXButton selectFiles;
    @FXML
    private JFXButton uploadFiles;
    @FXML
    private ListView filesList;
    
    
    //bead class table area 
    @FXML
    public TableView<bead> beadTable;  
    @FXML
    private TableColumn<bead, String> beadCol; 
    @FXML
    private TableColumn<bead, String> analyteCol;
    @FXML
    private JFXButton addBead; // not needed
    private TextField beadInput; // not needed
    private TextField analyteInput; // not needed
     
    
    //right-top area, display experiement info
    @FXML
    private ChoiceBox<?> DropdownExperimentsChoiceBox;
    @FXML
    private Text totalNumberOfExperiments;
    @FXML
    private Text currentExperiementNumber;
    @FXML
    private Text XMLfilesNames;
 
    // user input area 
    //input for plate1
    @FXML
    private TextField numSampleInput;
    @FXML
    private TextField numReplicaInput;
    @FXML
    private TextField numProbeInput;
    @FXML
    private TextField sampleNamesInput;
    @FXML
    private TextField numSampleInput2;
    @FXML
    private TextField numReplicaInput2;
    @FXML
    private TextField numProbeInput2;
    @FXML
    private TextField sampleNamesInput2;
    @FXML
    private JFXButton checkLayout;
   
    
    
// bead plates tab area, use for display proble layout 
    @FXML
    private TabPane beadPlatesTab;
    @FXML
    private Tab beadPlate1Tab;
    @FXML
    private Tab beadPlate2Tab;
    @FXML
    private GridPane beadPlate1Layout;
    @FXML
    private GridPane beadPlate2Layout;

    
//probe table area    
    @FXML
    private TableView<probeTableData> probeTable;
    @FXML
    private TableColumn<probeTableData, Integer> probeCol; 
    @FXML
    private TableColumn<?, ?> BeadPlate1ProbeCol;
    @FXML
    private TableColumn<?, ?> BeadPlate2ProbeCol;
    @FXML
    private JFXButton editProbesInBeadPlate1;
    @FXML
    private JFXButton editProbesInBeadPlate2;        

// analyze button      
    @FXML
    private JFXButton analyze;

//Beblow are elements used for displaying probe layout in bead plates.     
//colors list for set diffrent colors to each probe
    List<String> colors = new ArrayList<>(Arrays.asList("-fx-background-color:blue;", "-fx-background-color:yellow;", "-fx-background-color:red;", "-fx-background-color:pink;", 
            "-fx-background-color:green;", "-fx-background-color:orange;", "-fx-background-color:golden;", "-fx-background-color:purple;", "-fx-background-color:AQUA;",
            "-fx-background-color:BLUEVIOLET;", "-fx-background-color:#F5F5DC;", "-fx-background-color:BISQUE;", "-fx-background-color:brown;", "-fx-background-color:CORAL;"));
    List<GridPane> beadPlateLayouts = new ArrayList<>(); // list for layout gridPane 
    List<UserInputForBeadPlate> userInputsForBeadPlate = new ArrayList<>(); // list for user input data
    List<Tab> beadPlates = new ArrayList<>(); // list for bead plate tabs 
    List<TextField> layoutCellsList = new ArrayList<>();
    @FXML
    private Menu menuInput;
    @FXML
    private Menu meunMedianValues;
    @FXML
    private JFXButton DeleteBead;
    @FXML
    private TextField cell1;
    @FXML
    private TextField cell2;
    @FXML
    private TextField cell3;
    @FXML
    private TextField cell4;
    @FXML
    private TextField cell5;
    @FXML
    private TextField cell6;
    @FXML
    private TextField seven;
    @FXML
    private TextField eight;
    @FXML
    private TextField nine;
    @FXML
    private TextField ten;
    @FXML
    private AnchorPane exp2AP;
    @FXML
    private TextField cell11;
    @FXML
    private TextField cell21;
    @FXML
    private TextField cell31;
    @FXML
    private TextField cell41;
    @FXML
    private TextField cell51;
    @FXML
    private TextField cell61;
    @FXML
    private TextField seven1;
    @FXML
    private TextField eight1;
    @FXML
    private TextField nine1;
    @FXML
    private TextField ten1;

    
    

        @Override
    public void initialize(URL url, ResourceBundle rb) {      
        ObservableList<bead> beadsfromXML = FXCollections.observableArrayList();
        String filePath = "C:\\Appling\\CS Master\\Capstone\\AnalysiswithM\\110614_SEE3.xml";
        StAXParser parser = new StAXParser();
        beadsfromXML =  parser.getBeads(filePath);

        // set beads data into bead class List table. 
        beadCol.setCellValueFactory(new PropertyValueFactory<bead,String>("RegionNumber"));
        analyteCol.setCellValueFactory(new PropertyValueFactory<bead,String>("Analyte"));
        beadTable.setItems(beadsfromXML);
    }    

    // for upload file button. click the button to display files lists in a listview 
    @FXML
    private void selectFilesEvent(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("XML Files", "*.xml"));
        
        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if(selectedFiles!= null)
        {
            for(int i =0; i<selectedFiles.size(); i++)
            {
                filesList.getItems().add(selectedFiles.get(i).getName()); // display on the listview fileList. 
            }           
        }
        else
        {
            System.out.println("File is not valid");
        }
        
    }



    @FXML
    private void addBeadEvent(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBeads.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }

    }

    @FXML
    private void cancelBeadEvent(ActionEvent event) {
                    beadInput.clear();
            analyteInput.clear();
    }
    
    
    private void addGridLayouts()
    {
        beadPlateLayouts.add(beadPlate1Layout);
        beadPlateLayouts.add(beadPlate2Layout);
    }

    //for display layout button. click to diaplay bead plate layout on each bead plate tab. 
    @FXML
    private void checkLayoutEvent(ActionEvent event) {
       if(beadPlates.size()==0)
        {
        beadPlateLayouts.add(beadPlate1Layout);
        beadPlateLayouts.add(beadPlate2Layout);
        beadPlates.add(beadPlate1Tab);
        beadPlates.add(beadPlate2Tab);              
        }
       
       //gather user inputs for bead plate1 form each text fileds 
        int numberOfSamples = Integer.parseInt(numSampleInput.getText());
        int numberOfReps = Integer.parseInt(numReplicaInput.getText());
        int numberOfProbes =  Integer.parseInt(numProbeInput.getText());  
        String names = sampleNamesInput.getText();
        String[] nameList = names.split(",");
       // ObservableList<probeTableData> probeList= generateProbes(numberOfProbes); 
        List<String> probeList = new ArrayList<>();
        //combine user inputs and probeList into a UserInputForBeadPlate object
        userInputsForBeadPlate.add(new UserInputForBeadPlate(numberOfSamples, numberOfReps,nameList,numberOfProbes,probeList ));
        
        
        //gather user inputs for bead plate2 for each text fileds 
        int numberOfSamples2 = Integer.parseInt(numSampleInput2.getText());
        int numberOfReps2 = Integer.parseInt(numReplicaInput2.getText());
        int numberOfProbes2 =  Integer.parseInt(numProbeInput2.getText());  
        String names2 = sampleNamesInput2.getText();
        String[] nameList2 = names.split(",");
        List<String> probeList2 = new ArrayList<>();
               
        //generate probe list base on users input for # of probes
       // ObservableList<probeTableData> probeList2= generateProbes(numberOfProbes2); 
        //combine user inputs and probeList into a UserInputForBeadPlate object
        userInputsForBeadPlate.add(new UserInputForBeadPlate(numberOfSamples2, numberOfReps2,nameList2,numberOfProbes2,probeList2 ));
        
        //get index of current bead plate, pass index and userinput and probelist to display layout 
        //int index=beadPlatesTab.getSelectionModel().getSelectedIndex(); 
        for(int i =0; i <beadPlateLayouts.size();i++ )
        {
            displayLayout(i, userInputsForBeadPlate.get(i));
        }
        
        //create probe table datas for popolating data in probe table. 
        displayProbeTable();
    }
    
private void displayProbeTable() {
    List<Integer> counts = Arrays.asList(1, 2, 3, 4, 5);
    List<String> probeList1 = Arrays.asList("One", "Two", "Three", "Four", "Five");
    List<String> probeList2 = Arrays.asList("One", "Two", "Three", "Four", "Five");
    for (int i = 0; i < Math.max(probeList1.size(), probeList2.size()); i++) {
    probeTable.getItems().add(new probeTableData(counts.get(i), probeList1.get(i),probeList2.get(i)));
    } 
    
}


   
    //display layout in the gridpane
    // index: index of the bead plate 
    //data: user input data for that bead plate   
  private void displayLayout(int index, UserInputForBeadPlate data) {
        //fill cells with colors and values base on users' inputs
        layoutCellsList = getCells(beadPlateLayouts .get(index));
        int cellsCount =0;
        String[] nameList = data.getNames(); 
        int cellsToFill = data.getNumOfSamples()*data.getNumOfReplicas()*data.getNumOfProbes(); // totoal cells need to fill in in the grid pane
        int numberOfProbes=data.getNumOfProbes();
        int numberOfSamples = data.getNumOfSamples();
        int numberOfReps = data.getNumOfReplicas();
        while(cellsCount<cellsToFill)
        {
            for(int i = 0; i<numberOfProbes; i++)
            {
                String color = colors.get(i%colors.size());  // choose different color to each probe
                for(int j = 0; j<numberOfSamples * numberOfReps; j++)
                {
                    layoutCellsList.get(cellsCount).setStyle(color);  // set color to those cells.
                    layoutCellsList.get(cellsCount).setText(nameList[cellsCount%(nameList.length)] + "." + ((j)/numberOfSamples+1)); //set probe text to those cells
                    cellsCount++;
                }
            }
        }       
        
        ///populate initial data into probe table
        //List<String> probeList=data.getProbeList();
        //probeTable.setItems(probeList);
        /*
        probeTable.setRowFactory(tv-> new TableRow<probeTableData>()
        {
            @Override
            protected void updateItem(probeTableData item, boolean empty)
            {
                if (item == null)return;
                Integer n = item.getProbe();
                int index = Integer.parseInt(n.toString());
                setStyle(colors.get(index-1));
            }
        }
        );
*/
  }

    
    private int getSeletedTabIndex() {
        for(int i = 0; i<beadPlates .size();i++)
            if(beadPlates .get(i).isSelected())
            {
                return i;
            }
        return -1;
    }

    //
    public ObservableList<probeTableData> generateProbes(int n) {
        ObservableList<probeTableData> allData = FXCollections.observableArrayList();
        for (int i = 0; i < n; i++) {
            allData.add(new probeTableData(i+1, "", ""));
        }
        return allData;
    }
    
    
    
    
    public List<TextField> getCells(GridPane gridPane)
    {
        List<TextField> cells = new ArrayList<>();
            for(Node currentNode : gridPane.getChildren())
            {
                if (currentNode instanceof TextField)
                {
                    cells.add((TextField)currentNode);
                }
            }
        return cells;
    }
            
    public Node getNodeByRowColumnIndex (final int column, final int row, GridPane gridPane) {
     System.out.println("enter getNode function" );    
    Node result = null;
    ObservableList<Node> childrens = gridPane.getChildren();

    for (Node node : childrens) {
        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
            result = node;
            break;
        }
    }
     System.out.println("leave getNode function" );    
    return result;
}
    

    @FXML
    private void analyzeEvent(ActionEvent event) {
    }



    @FXML
    private void changeExperimentEvent(Event event) {
        int index=beadPlatesTab.getSelectionModel().getSelectedIndex();
        if(userInputsForBeadPlate .size()<(index+1))
           {
           numSampleInput.clear();
           numReplicaInput.clear();
           sampleNamesInput.clear();
           numProbeInput.clear();
           probeTable.getItems().clear();
           }
        else
        {
             //fill textfile
            numSampleInput.setText(String.valueOf(userInputsForBeadPlate .get(index).getNumOfSamples()));
            displayLayout(index, userInputsForBeadPlate .get(index));
        }   
    }



    @FXML
    private void uploadFilesEvent(ActionEvent event) {
    }



    private void editBeadFileEvent(ActionEvent event) throws IOException {
       // Runtime rt = Runtime.getRuntime();
        //String path = javafxapplication1.class.getProtectionDomain().getCodeSource().getLocation().getPath();
       // String file = "C:\\Users\\feiping\\Documents\\NetBeansProjects\\JavaFXApplication1\\src\\javafxapplication1\\beads.txt";
       // Process p = rt.exec("notepad" + file);
        
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "C:\\Users\\feiping\\Documents\\NetBeansProjects\\JavaFXApplication1\\src\\javafxapplication1\\beads.txt");
        pb.start();
        
    }



    @FXML
    private void cancelProbeEvent(ActionEvent event) {
    }





}
