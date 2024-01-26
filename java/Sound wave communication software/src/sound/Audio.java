package sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class Audio {
	public static byte[] cal(double[] freq, AudioFormat format, int n) {
		double[] frequencies =freq;
	    double duration = 0.5; // 출력할 시간(초) 설정
	    int numOfFrequencies = frequencies.length;
	    byte[][] buffers = new byte[numOfFrequencies][(int) (format.getSampleRate() * duration)];
	    for (int j = 0; j < numOfFrequencies; j++) {
	        for (int i = 0; i < buffers[j].length; i++) {
	            double angle = 1.0 * Math.PI * frequencies[j] * i / format.getSampleRate();
	            buffers[j][i] = (byte) (Math.sin(angle) * 127); // 해당 주파수에 해당하는 데이터 생성
	        }
	    }
	    // 모든 주파수의 배열을 합쳐서 최종 buffer 생성
	    int maxLength = 0;
	    for (int j = 0; j < numOfFrequencies; j++) {
	        maxLength = Math.max(maxLength, buffers[j].length);
	    }
	    byte[] buffer = new byte[maxLength];
	    for (int i = 0; i < maxLength; i++) {
	        double sum = 0;
	        for (int j = 0; j < numOfFrequencies; j++) {
	            byte sample = (i < buffers[j].length) ? buffers[j][i] : 0;
	            sum += sample;
	        }
	        buffer[i] = (byte) (sum / numOfFrequencies); // 주파수 데이터를 평균하여 합침
	    }
	    return buffer;
	}
	public static void sound(double[][] freq, int n) {
		try {
            // 오디오 포맷 설정
            AudioFormat format = new AudioFormat(30000, 16, 1, true, true);
            n=1;
            // 오디오 장치 열기
            Line.Info info = new Line.Info(SourceDataLine.class);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            // 주파수 생성
            byte[][] buffer = {};
            buffer = new byte[freq.length][7];
            for(int i=0;i<freq.length;i++) {
            	byte[] arr = cal(freq[i], format, n);
            	buffer[i] = arr;
            }
            line.start();
            for (int k = 0; k < buffer.length; k++) {
                line.write(buffer[k], 0, buffer[k].length);
                
            }
            line.drain();
            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	
	public void play(double[][] frequency) {
		sound(frequency,1);
	}
	
    public static void main(double[][] frequency) {
    	sound(frequency,1);
    }
}
