import java.util.Scanner;

//author by Sulthan ---SUL
public class FixBookUI 
{

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED }; //UI_STATE to UiState -- SUL

	private FixBookControl control; //CoNtRoL to control -- SUL
	private Scanner input;
	private UiState state; //StAtE to state UI_STATE to UiState -- SUL

	
	public FixBookUI(FixBookControl control) 
	{
		this.control = control; //CoNtRoL to control -- SUL
		input = new Scanner(System.in);
		state = UiState.INITIALISED; //StAtE to state UI_STATE to UiState -- SUL
		control.Set_Ui(this);
	}


	public void Set_State(UiState state) 
	{
		this.state = state; //StAtE to state -- SUL
	}

	
	public void Run() //RuN to Run -- SUL
	{
		output("Fix Book Use Case UI\n");
		
		while (true) 
			{
			
			switch (state) 
			{ //StAtE to state -- SUL
			
			case READY:
				String Book_STR = input("Scan Book (<enter> completes): ");
				if (Book_STR.length() == 0) {
					control.Scanning_complete(); //CoNtRoL to control SCanning_Complete to Scanning_complete -- SUL
				}
				else 
					{
					try 
					{
						int Book_ID = Integer.valueOf(Book_STR).intValue();
						control.Book_scanned(Book_ID); //CoNtRoL to control -- SUL
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : "); //AnS to ans -- SUL
				boolean Fix = false;
				if (ans.toUpperCase().equals("Y")) 
				{ //AnS to ans -- SUL
					Fix = true; //FiX to Fix -- SUL
				}
				control.FIX_Book(Fix); //CoNtRoL to control -- SUL
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);	//StAtE to state -- SUL		
			
			}		
		}
		
	}

	
	private String input(String prompt) 
	{
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) 
	{
		System.out.println(object);
	}
	

	public void display(Object object) 
	{
		output(object);
	}
	
	
}