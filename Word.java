public class Word {
    private String word_target;
    private String word_explain;

    Word(){
        
    }

    Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public void setWordtarget(String word_target) {
        this.word_target = word_target;
    }

    public String getWordtarget() {
        return this.word_target;
    }

    public void setWordexplain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWordexplain() {
        return this.word_explain;
    }
}