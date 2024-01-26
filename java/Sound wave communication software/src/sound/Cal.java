package sound;

public class Cal {
	
	private double[][] frequencies = {};
	private double[] frequency_list = {4000.0,5000.0,6000.0,7000.0,8000.0,9000.0,10000.0};
	
	private int[] ascii = {};
	private String[] two = {};
	public int calculate(String msg) {
		ascii = new int[msg.length()];
		two = new String[msg.length()];
		frequencies = new double[msg.length()][8];
		
		//convert to ascii code
		for(int i=0;i<msg.length();i++) {
			char ch = msg.charAt(i);
			if(ch>127) {
				return 0;
			}
			ascii[i] = (int)ch;
		}
		//convert to BinaryString
		for(int i=0;i<msg.length();i++) {
			two[i] = Integer.toBinaryString(ascii[i]);
			while(two[i].length()<7) {
				two[i] = "0" + two[i];
			}
		}
		for(int i=0;i<msg.length();i++) {
			int index=0;
			for(int j=0;j<7;j++) {
				if(two[i].charAt(j)=='1') {
					frequencies[i][index]=frequency_list[j];
					index++;
				}
			}
			frequencies[i][7]=13000.0;
		}
		Audio audio = new Audio();
		audio.play(frequencies);
//		for(int j=0;j<msg.length();j++) {
//			for(int i=0;i<7;i++) {
//				System.out.print(frequencies[j][i]+" ");
//			}
//			System.out.println();
//		}
		
		
		
		
		
		return 1;
	}
	public static void main(String msg) {
		
	}
}
