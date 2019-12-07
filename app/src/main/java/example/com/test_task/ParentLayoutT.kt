package example.com.test_task

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import java.util.ArrayList


class ParentLayoutT(internal var context: Context) : RelativeLayout(context) {

    val TAG = "TableMainLayout.java"

    //set the header titles
    internal var headers = arrayOf("Header 1", "Header 2", "Header 3", "Header 4", "Header 5", "Header 6", "Header 7", "Header 8", "Header 9")

    //Tables to handle data
    lateinit var tableA: TableLayout
    lateinit var tableB: TableLayout
    lateinit var tableC: TableLayout
    lateinit var tableD: TableLayout

    //Scrollings for behavior of data
    lateinit var horizontalScrollViewB: HorizontalScrollView
    lateinit var horizontalScrollViewD: HorizontalScrollView

    lateinit var scrollViewC: ScrollView
    lateinit var scrollViewD: ScrollView
    internal var sampleObjects = this.sampleObjects()

    //setting headers cells width
    internal var headerCellsWidth = IntArray(headers.size)

    init {

        // initialize the main components (TableLayouts, HorizontalScrollView, ScrollView)
        this.initComponents()
        this.setComponentsId()
        this.setScrollViewAndHorizontalScrollViewTag()

        // no need to assemble component A, since it is just a table
        this.horizontalScrollViewB.addView(this.tableB)

        this.scrollViewC.addView(this.tableC)

        this.scrollViewD.addView(this.horizontalScrollViewD)
        this.horizontalScrollViewD.addView(this.tableD)

        // add the components to be part of the main layout
        this.addComponentToMainLayout()
        this.setBackgroundColor(Color.RED)


        // add some table rows
        this.addTableRowToTableA()
        this.addTableRowToTableB()

        //size to header
        this.resizeHeaderHeight()

        //getting header row cell width
        this.getTableRowHeaderCellWidth()
        // generate table row of table C and table D
        this.generateTableC_AndTable_B()
        // resize body table row height
        this.resizeBodyTableRowHeight()

    }

    // this is just the sample data
    internal fun sampleObjects(): List<DummyObject> {

        val sampleObjects = ArrayList<DummyObject>()

        for (x in 1..20) {

            val sampleObject = DummyObject(
                "Col 1, Row $x",
                "Col 2, Row $x",
                "Col 3, Row $x",
                "Col 4, Row $x",
                "Col 5, Row $x",
                "Col 6, Row $x",
                "Col 7, Row $x",
                "Col 8, Row $x",
                "Col 9, Row $x"
            )

            sampleObjects.add(sampleObject)
        }
        return sampleObjects

    }

    // initalized components
    private fun initComponents() {

        this.tableA = TableLayout(this.context)
        this.tableB = TableLayout(this.context)
        this.tableC = TableLayout(this.context)
        this.tableD = TableLayout(this.context)

        this.horizontalScrollViewB = MyHorizontalScrollView(this.context)
        this.horizontalScrollViewD = MyHorizontalScrollView(this.context)

        this.scrollViewC = MyScrollView(this.context)
        this.scrollViewD = MyScrollView(this.context)

        this.tableA.setBackgroundColor(Color.GREEN)
        this.horizontalScrollViewB.setBackgroundColor(Color.LTGRAY)

    }

    // set essential component IDs
    @SuppressLint("ResourceType")
    private fun setComponentsId() {
        this.tableA.id = 1
        this.horizontalScrollViewB.id = 2
        this.scrollViewC.id = 3
        this.scrollViewD.id = 4
    }

    // set tags for some horizontal and vertical scroll view
    private fun setScrollViewAndHorizontalScrollViewTag() {

        this.horizontalScrollViewB.tag = "horizontal scroll view b"
        this.horizontalScrollViewD.tag = "horizontal scroll view d"

        this.scrollViewC.tag = "scroll view c"
        this.scrollViewD.tag = "scroll view d"
    }

    // we add the components here in our TableMainLayout
    private fun addComponentToMainLayout() {

        // RelativeLayout params were very useful here
        // the addRule method is the key to arrange the components properly
        val componentB_Params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        componentB_Params.addRule(RelativeLayout.RIGHT_OF, this.tableA.id)

        val componentC_Params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        componentC_Params.addRule(RelativeLayout.BELOW, this.tableA.id)

        val componentD_Params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        componentD_Params.addRule(RelativeLayout.RIGHT_OF, this.scrollViewC.id)
        componentD_Params.addRule(RelativeLayout.BELOW, this.horizontalScrollViewB.id)

        // 'this' is a relative layout,
        // we extend this table layout as relative layout as seen during the creation of this class
        this.addView(this.tableA)
        this.addView(this.horizontalScrollViewB, componentB_Params)
        this.addView(this.scrollViewC, componentC_Params)
        this.addView(this.scrollViewD, componentD_Params)

    }

    private fun addTableRowToTableA() {
        this.tableA.addView(this.componentATableRow())
    }

    private fun addTableRowToTableB() {
        this.tableB.addView(this.componentBTableRow())
    }

    // generate table row of table A
    internal fun componentATableRow(): TableRow {

        val componentATableRow = TableRow(this.context)
        componentATableRow.setBackgroundColor(Color.GRAY)
        val params = TableRow.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(2, 0, 0, 0)

        val textView = this.headerTextView(this.headers[0])
        textView.setBackgroundColor(context.resources.getColor(R.color.colorLowRed))
        val textView1 = this.headerTextView(this.headers[1])
        textView1.setBackgroundColor(context.resources.getColor(R.color.colorLowRed))

        componentATableRow.addView(textView, params)
        componentATableRow.addView(textView1, params)

        return componentATableRow
    }

    // generate table row of table B
    internal fun componentBTableRow(): TableRow {

        val componentBTableRow = TableRow(this.context)
        val headerFieldCount = this.headers.size

        val params = TableRow.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(2, 0, 0, 0)

        for (x in 0 until headerFieldCount - 2) {
            val textView = this.headerTextView(this.headers[x + 2])
            componentBTableRow.addView(textView, params)
        }

        return componentBTableRow
    }

    // generate table row of table C and table D
    fun generateTableC_AndTable_B() {

        // just seeing some header cell width
        for (x in this.headerCellsWidth.indices) {
            Log.v("TableMainLayout.java", this.headerCellsWidth[x].toString() + "")
        }

        for (sampleObject in this.sampleObjects) {

            val tableRowForTableC = this.tableRowForTableC(sampleObject)
            val taleRowForTableD = this.taleRowForTableD(sampleObject)

            tableRowForTableC.setBackgroundColor(Color.LTGRAY)
            taleRowForTableD.setBackgroundColor(Color.LTGRAY)

            this.tableC.addView(tableRowForTableC)
            this.tableD.addView(taleRowForTableD)

        }
    }

    // a TableRow for table C
    internal fun tableRowForTableC(sampleObject: DummyObject): TableRow {

        val tableRowForTableC = TableRow(this.context)

        val params = TableRow.LayoutParams(headerCellsWidth[0], RelativeLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(2, 2, 0, 0)

        val textView = this.bodyTextView(sampleObject.header1)
        textView.setBackgroundColor(context.resources.getColor(R.color.colorLightGry))
        val textView1 = this.bodyTextView(sampleObject.header2)
        textView1.setBackgroundColor(context.resources.getColor(R.color.colorLightGry))

        tableRowForTableC.addView(textView, params)
        tableRowForTableC.addView(textView1, params)

        return tableRowForTableC
    }

    internal fun taleRowForTableD(sampleObject: DummyObject): TableRow {

        val taleRowForTableD = TableRow(this.context)

        val loopCount = (this.tableB.getChildAt(0) as TableRow).childCount
        val info = arrayOf(sampleObject.header3, sampleObject.header4, sampleObject.header5, sampleObject.header6, sampleObject.header7, sampleObject.header8, sampleObject.header9)

        for (x in 0 until loopCount) {
            val params = TableRow.LayoutParams(headerCellsWidth[x + 2], RelativeLayout.LayoutParams.MATCH_PARENT)
            params.setMargins(2, 2, 0, 0)

            val textViewB = this.bodyTextView(info[x])
            taleRowForTableD.addView(textViewB, params)
        }

        return taleRowForTableD

    }

    // table cell standard TextView
    internal fun bodyTextView(label: String): TextView {

        val bodyTextView = TextView(this.context)
        bodyTextView.setBackgroundColor(Color.WHITE)
        bodyTextView.text = label
        bodyTextView.gravity = Gravity.CENTER
        bodyTextView.setPadding(5, 5, 5, 5)

        return bodyTextView
    }

    // header standard TextView
    internal fun headerTextView(label: String): TextView {

        val headerTextView = TextView(this.context)
        headerTextView.setBackgroundColor(Color.WHITE)
        headerTextView.text = label
        headerTextView.gravity = Gravity.CENTER
        headerTextView.setPadding(5, 5, 5, 5)

        return headerTextView
    }

    // resizing TableRow height starts here
    internal fun resizeHeaderHeight() {

        val productNameHeaderTableRow = this.tableA.getChildAt(0) as TableRow
        val productInfoTableRow = this.tableB.getChildAt(0) as TableRow

        val rowAHeight = this.viewHeight(productNameHeaderTableRow)
        val rowBHeight = this.viewHeight(productInfoTableRow)

        val tableRow = if (rowAHeight < rowBHeight) productNameHeaderTableRow else productInfoTableRow
        val finalHeight = if (rowAHeight > rowBHeight) rowAHeight else rowBHeight

        this.matchLayoutHeight(tableRow, finalHeight)
    }

    internal fun getTableRowHeaderCellWidth() {

        val tableAChildCount = (this.tableA.getChildAt(0) as TableRow).childCount
        val tableBChildCount = (this.tableB.getChildAt(0) as TableRow).childCount

        for (x in 0 until tableAChildCount + tableBChildCount) {

            if (x == 0) {
                this.headerCellsWidth[x] = this.viewWidth((this.tableA.getChildAt(0) as TableRow).getChildAt(x))
            } else if (x == 1) {
                this.headerCellsWidth[x] = this.viewWidth((this.tableA.getChildAt(0) as TableRow).getChildAt(x))
            } else {
                this.headerCellsWidth[x] = this.viewWidth((this.tableB.getChildAt(0) as TableRow).getChildAt(x - 2))
            }

        }
    }

    // resize body table row height
    internal fun resizeBodyTableRowHeight() {

        val tableC_ChildCount = this.tableC.childCount

        for (x in 0 until tableC_ChildCount) {

            val productNameHeaderTableRow = this.tableC.getChildAt(x) as TableRow
            val productInfoTableRow = this.tableD.getChildAt(x) as TableRow

            val rowAHeight = this.viewHeight(productNameHeaderTableRow)
            val rowBHeight = this.viewHeight(productInfoTableRow)

            val tableRow = if (rowAHeight < rowBHeight) productNameHeaderTableRow else productInfoTableRow
            val finalHeight = if (rowAHeight > rowBHeight) rowAHeight else rowBHeight

            this.matchLayoutHeight(tableRow, finalHeight)
        }

    }

    // match all height in a table row
    // to make a standard TableRow height
    private fun matchLayoutHeight(tableRow: TableRow, height: Int) {

        val tableRowChildCount = tableRow.childCount

        // if a TableRow has only 1 child
        if (tableRow.childCount == 1) {

            val view = tableRow.getChildAt(0)
            val params = view.layoutParams as TableRow.LayoutParams
            params.height = height - (params.bottomMargin + params.topMargin)

            return
        }

        // if a TableRow has more than 1 child
        for (x in 0 until tableRowChildCount) {

            val view = tableRow.getChildAt(x)

            val params = view.layoutParams as TableRow.LayoutParams

            if (!isTheHeighestLayout(tableRow, x)) {
                params.height = height - (params.bottomMargin + params.topMargin)
                return
            }
        }

    }

    // check if the view has the highest height in a TableRow
    private fun isTheHeighestLayout(tableRow: TableRow, layoutPosition: Int): Boolean {

        val tableRowChildCount = tableRow.childCount
        var heighestViewPosition = -2
        var viewHeight = 0

        for (x in 0 until tableRowChildCount) {
            val view = tableRow.getChildAt(x)
            val height = this.viewHeight(view)

            if (viewHeight < height) {
                heighestViewPosition = x
                viewHeight = height
            }
        }

        return heighestViewPosition == layoutPosition
    }

    // read a view's height
    private fun viewHeight(view: View): Int {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        return view.measuredHeight
    }

    // read a view's width
    private fun viewWidth(view: View): Int {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        return view.measuredWidth
    }

    // horizontal scroll view custom class
    internal inner class MyHorizontalScrollView(context: Context) : HorizontalScrollView(context) {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            val tag = this.tag as String

            if (tag.equals("horizontal scroll view b", ignoreCase = true)) {
                horizontalScrollViewD.scrollTo(l, 0)
            } else {
                horizontalScrollViewB.scrollTo(l, 0)
            }
        }
    }

    // scroll view custom class
    internal inner class MyScrollView(context: Context) : ScrollView(context) {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {

            val tag = this.tag as String

            if (tag.equals("scroll view c", ignoreCase = true)) {
                scrollViewD.scrollTo(0, t)
            } else {
                scrollViewC.scrollTo(0, t)
            }
        }
    }
}