package sound;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jtransforms.fft.DoubleFFT_1D;

public class Input {
    public static void main(String[] args) {
        String filePath = "src/music/test.wav"; // 분석할 MP3 파일 경로
        
        try {
            // MP3 파일 로드
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            
            // 오디오 데이터 읽기
            byte[] audioData = new byte[(int) audioInputStream.getFrameLength() * audioInputStream.getFormat().getFrameSize()];
            audioInputStream.read(audioData);
            
            // 스펙트로그램 계산
            double[] audioSamples = new double[audioData.length / 2];
            for (int i = 0, j = 0; i < audioData.length; i += 2, j++) {
                short audioSample = (short) ((audioData[i] & 0xFF) | (audioData[i + 1] << 8));
                audioSamples[j] = audioSample / 32768.0; // 정규화
            }
            
            int fftSize = 1024; // FFT 크기
            int hopSize = 512; // 오버랩 크기
            
            int spectrogramWidth = audioSamples.length / hopSize;
            double[][] spectrogram = new double[fftSize / 2][spectrogramWidth];
            
            DoubleFFT_1D fft = new DoubleFFT_1D(fftSize);
            
            for (int i = 0; i < spectrogramWidth; i++) {
                double[] frame = Arrays.copyOfRange(audioSamples, i * hopSize, i * hopSize + fftSize);
                
                fft.realForward(frame);
                
                for (int j = 0; j < fftSize / 2; j++) {
                    double magnitude = Math.sqrt(frame[2 * j] * frame[2 * j] + frame[2 * j + 1] * frame[2 * j + 1]);
                    spectrogram[j][i] = magnitude;
                }
            }
            
            // 스펙트로그램 출력
            for (int i = 0; i < spectrogram.length; i++) {
                for (int j = 0; j < spectrogram[i].length; j++) {
                    System.out.print(spectrogram[i][j] + " ");
                }
                System.out.println();
            }
            
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
