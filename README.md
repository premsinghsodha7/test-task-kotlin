# test-task-kotlin
andorid Kotlin reporting table where 2 header and 2 column fix and other rows and columns should be vertically and horizontally scrollable.


        //method initialize componets to resources
        this.initComponents();
        
        //dynamically set essential component IDs
        this.setComponentsId();
        
        //initialize the tag to scrollviews for setting behavior by tag
        this.setScrollViewAndHorizontalScrollViewTag();

        // adding childview to horizotalscrollview where horizotalscrollviewB -> tableB
        this.horizontalScrollViewB.addView(this.tableB);

        // adding childview to scrollview where scrollViewC -> tableC
        this.scrollViewC.addView(this.tableC);

        // adding childview to scrollview where scrollViewD -> horizontalScrollViewD
        this.scrollViewD.addView(this.horizontalScrollViewD);
        
        // adding childview to horizotalscrollview where horizontalScrollViewD -> tableD
        this.horizontalScrollViewD.addView(this.tableD);

        // add the components to be part of the Parent Layout
        this.addComponentToMainLayout();
        this.setBackgroundColor(Color.RED);


        // add row to tableA 
        this.addTableRowToTableA();
        
        // add row to tableB 
        this. addTableRowToTableB();

        //Resize the header height
        this.resizeHeaderHeight();

        //getting width of header row for setting child rows
        this.getTableRowHeaderCellWidth();
        
        // generate table row of table C and table D 
        this.generateTableC_AndTable_B();
        
        // resize body table row height
        this.resizeBodyTableRowHeight();
        
        
_**this is code structure how it implemented**_

![code_structure](https://user-images.githubusercontent.com/30569054/70380804-9bd2e300-1962-11ea-88f4-42dedcd0d550.png)

_**this is table structure**_

![table_structure](https://user-images.githubusercontent.com/30569054/70380805-9bd2e300-1962-11ea-97d5-c40a7eb627f8.png)

**_Output_** 

![Screenshot_20200108-020325](https://user-images.githubusercontent.com/30569054/70380819-de94bb00-1962-11ea-8b21-24bd755f55bf.png)

        
