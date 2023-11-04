public class DictionaryCommandline {

    DictionaryManagement show = new DictionaryManagement();
    
    public void showAllWords() {
        // show.showWords();
        show.showWordsFile();
    }

    public void dictionaryBasic() {
        // show.insertFromCommandline();
        show.insertFromFile();
        // show.dictionaryLookup();
        // show.dictionarySearcher();
        show.dictionaryRemove();
        showAllWords();
        
    }
}
