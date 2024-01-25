//package sound;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Line;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//
//public class sound2 {
//	public static void main(String[] args) {
//		try {
//            AudioFormat format = new AudioFormat(22000, 16, 1, true, true);
//
//            // 오디오 장치 열기
//            Line.Info info = new Line.Info(SourceDataLine.class);
//            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
//            line.open(format);
//
//            // 주파수 생성
//            double[] frequency= {4000.0,5000.0};
//            double duration = 2.0; // 출력할 시간(초) 설정
//            byte[] buffer ;
//            for(int i=0;i<frequency.length;i++) {
//            	
//            }
//            byte[] buffer1 = new byte[(int) (format.getSampleRate() * duration)];
//            byte[] buffer2 = new byte[(int) (format.getSampleRate() * duration)];
//            byte[] buffer;
//
//            for (int i = 0; i < buffer1.length; i++) {
//                double angle1 = 1.0 * Math.PI * frequency1 * i / format.getSampleRate();
//                buffer1[i] = (byte) (Math.sin(angle1) * 127); // 첫 번째 주파수에 해당하는 데이터 생성
//
//                double angle2 = 1.0 * Math.PI * frequency2 * i / format.getSampleRate();
//                buffer2[i] = (byte) (Math.sin(angle2) * 127); // 두 번째 주파수에 해당하는 데이터 생성
//                
//            }
//
//            // 두 개의 배열을 합쳐서 최종 buffer 생성
//            int maxLength = Math.max(buffer1.length, buffer2.length);
//            buffer = new byte[maxLength];
//            for (int i = 0; i < maxLength; i++) {
//                byte sample1 = (i < buffer1.length) ? buffer1[i] : 0;
//                byte sample2 = (i < buffer2.length) ? buffer2[i] : 0;
//                buffer[i] = (byte) ((sample1 + sample2) / 2); // 두 주파수 데이터를 평균하여 합침
//            }
//
//            // 오디오 출력
//            line.start();
//            line.write(buffer, 0, buffer.length);
//            line.drain();
//            line.close();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
//	}
//}
