package MyAssemblyAIProject;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;

public class SpeechToText {
    public static void main(String[] args) {
        AssemblyAI aai = AssemblyAI.builder().apiKey("<YOUR_API_KEY>").build();
        String url = "<AUDIO_URL>";
        var params = TranscriptOptionalParams.builder()
                .speakerLabels(true)
                .build();

        Transcript transcript = aai.transcripts().transcribe(url, params);
        if (transcript.getStatus().equals("error")) {
            System.err.println(transcript.getError());
        }
        StringBuilder fullTranscription = new StringBuilder();

        transcript.getUtterances().ifPresent(utterances ->
            utterances.forEach(utterance ->
                fullTranscription.append("Speaker ")
                                .append(utterance.getSpeaker())
                                .append(": ")
                                .append(utterance.getText())
                                .append("\n"))
        );
        String result = fullTranscription.toString();
        WriteToFile obj = new WriteToFile();
        obj.write(result);
    }
}