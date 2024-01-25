package sound;

public class Cal {
	
	private int[] ascii = {};
	private String[] two = {};
	public void calculate(String msg) {
		ascii = new int[msg.length()];
		two = new String[msg.length()];
		//convert to ascii code
		for(int i=0;i<msg.length();i++) {
			char ch = msg.charAt(i);
			ascii[i] = (int)ch;
		}
		//convert to BinaryString
		for(int i=0;i<msg.length();i++) {
			two[i] = Integer.toBinaryString(ascii[i]);
		}
	}
	public static void main(String msg) {
		
	}
}
