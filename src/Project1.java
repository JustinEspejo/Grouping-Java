/*
 * Justin Espejo
 * Project 1
 * CS323
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Project1 {
	private static String txt = "";

	public static void main(String[] args) {
		if(args == null || args.length == 0) System.out.println("You did not enter the name of the file.");
		else {
			try {
				readFile(args[0]);
			} catch (IOException e) {
				System.out.println("That File Does NOT Exist!!!");
			}
		}

	}


	/**
	 * This reads the fileinput, store the data to the binary tree until there are 
	 * no more lines then prints it out.
	 * @param fileName is name of the file to be read 
	 * @throws IOException
	 */
	private static void readFile(String fileName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while (line != null) {
			if (line.equals("")) {
				line = br.readLine();
				continue;
			}
			txt = txt + "Inputted text : "+ line + "\n";
			analyze(line);
			//System.out.println();
			line = br.readLine();
		} //end of while loop

		System.out.println(txt); //output in console
		printtoTxt(txt); //save to .txt file
	}

	private static void analyze(String line) {

		//	System.out.println("Group 0 : " + line);
		txt = txt + "Group 0 : " + line + "\n";
		int groupNum = 1;

		for (int i=0; i<line.length(); i++){ //start at the beginning of the equation and move down to end.
			String a = Character.toString(line.charAt(i)); 
			String openP = "("; 
			if (a.equals(openP)){ // points to current character and checks if its an open parenthesis.

				int counter = 1;
				int check = i+1; //moves onto the next character to check
				String output = "(";
				while (counter!=0){ //while loop to traverse inside the parenthesis. if counter falls to 0 exit while loop, means matching closing parenthesis is satisfied.
					String current = Character.toString(line.charAt(check));

					if (current.equals(")")){ 
						output = output + current;
						counter--;
						check++;
					}
					else if(current.equals(openP)){
						output = output + current;
						counter++;
						check++;
					}
					else {
						output = output + current;	
						check++;
					}
				}
				if(!output.equals(line)){
					String finalOutput = "Group "+ groupNum +": " + output;
					txt = txt + finalOutput + "\n"; 
					//	System.out.println(finalOutput);
					groupNum++;
				}
			}
		}		
		txt = txt + "\n";
	}	

	private static void printtoTxt(String str) throws FileNotFoundException{
		PrintWriter pw1 = new PrintWriter(new File("Output.txt"));
		pw1.print(txt);
		pw1.close();
		
		////		** IF OUTPUT WANTS TO BE SAVED AND NAMED SPECIFICALLY, USE CODE BELOW. **
		//		
		//		JFileChooser save = new JFileChooser();
		//		// Start in current directory
		//		//save.setCurrentDirectory(new File((System.getProperty("user.home") + "\\Desktop")));
		//		// Set filter for Java source files.
		//		save.setDialogTitle("Specify duplicate output file name and location");
		//		save.setFileFilter(new javax.swing.filechooser.FileFilter() {
		//			public boolean accept(File f) {
		//				String name = f.getName().toLowerCase();
		//				return name.endsWith(".txt") || f.isDirectory();
		//			}
		//
		//			public String getDescription() {
		//				return ".txt";
		//			}
		//		});
		//		File fFile = new File("output");
		//		// Set to a default name for save.
		//		save.setSelectedFile(fFile);
		//
		//		// Open chooser dialog
		//		int result2 = save.showSaveDialog(null);
		//
		//		if (result2 == JFileChooser.CANCEL_OPTION) {
		//			//TheGUI.sendMessage("You chose not to save the file?\n");
		//			return;
		//		} else if (result2 == JFileChooser.APPROVE_OPTION) {
		//			fFile = save.getSelectedFile();
		//			if (fFile.exists()) {
		//				int response = JOptionPane.showConfirmDialog(null,
		//						"Overwrite existing file?", "Confirm Overwrite",
		//						JOptionPane.OK_CANCEL_OPTION,
		//						JOptionPane.QUESTION_MESSAGE);
		//				if (response == JOptionPane.CANCEL_OPTION)
		//					return;
		//			}
		//			try {
		//				PrintWriter pw = new PrintWriter(fFile);
		//				pw.print(txt);
		//				pw.close();
		//			}
		//			catch(Exception e){
		//				System.out.println("Exception Found: " + e.getStackTrace());
		//			}
		//		}
		//
		//
	}

}
