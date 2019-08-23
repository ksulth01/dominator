public class FixBookControl {
	
	//author by Sulthan --SUL
	private FixBookUI UI;
	private enum ControlState { INITIALISED, READY, FIXING }; //CONTROL_STATE to ControlState -- SUL
	private ControlState state; //StAtE to state -- SUL
	
	private library LIB;
	private book curBook; //removed underscore and capatilized first letter -- SUL


	public FixBookControl() 
	{
		this.LIB = LIB.INSTANCE();
		state = ControlState.INITIALISED; //StAtE to state -- SUL
	}
	
	
	public void set_Ui(FixBookUI ui) //Set to set -- SUL
	{
		if (!state.equals(ControlState.INITIALISED)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = ui;
		ui.set_State(FixBookUI.UI_STATE.READY); //Set to set -- SUL
		state = ControlState.READY;		//StAtE to state -- SUL
	}


	public void bookScanned(int bookId) //Book_Scanned to bookScanned --SUL
	{
		if (!state.equals(ControlState.READY)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		curBook = LIB.Book(bookId); //removed underscore and capatilized first letter -- SUL
		
		if (curBook == null) //removed underscore and capatilized first letter -- SUL
		{
			UI.display("Invalid bookId");
			return;
		}
		if (!curBook.IS_Damaged()) //removed underscore and capatilized first letter -- SUL
		{
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(Cur_Book.toString());
		UI.Set_State(FixBookUI.UI_STATE.FIXING);
		state = ControlState.FIXING;	//StAtE to state -- SUL	
	}


	public void fixBook(boolean MUST_fix) //FIX_Book to fixBook -- SUL
	{
		if (!state.equals(ControlState.FIXING)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) 
		{
			LIB.Repair_BOOK(curBook); //removed underscore and capatilized first letter -- SUL
		}
		curBook = null; //removed underscore and capatilized first letter -- SUL
		UI.Set_State(FixBookUI.UI_STATE.READY);
		state = ControlState.READY;	//StAtE to state -- SUL	
	}

	
	public void scanningComplete()   //changed irregular capatilized words into order -SUL
	{
		if (!state.equals(ControlState.READY)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		UI.Set_State(FixBookUI.UI_STATE.COMPLETED);		
	}
}