# ScrabbleSolver

Scrabble Solver Service

Summary

 An HTTP REST service that returns Scrabble words for a given set of letters. This service used JAS-RS(The Java API for RESTful Web Services) with Jersey library

Data source

  Use the list of words here: http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt

API Specification

  The REST web service runs on port 8080 and responds to a URL of the pattern http://hostname:8080/words/<letters>, where <letters> is a string of arbitrary letters. There are two cases that need to be covered:

    1. The dictionary contains words that can be spelled with the given letters.

      A JSON list of strings is returned, where each entry is a word. They are sorted by Scrabble score, from highest to lowest scoring. For example:

      Request URL:       http://hostname:8080/words/hat
      Expected response: ["hat","ah","ha","th","at","a"]

      The letters are like Scrabble tiles. Order is unimportant:

      Request URL:       http://hostname:8080/words/aht
      Expected response: ["hat","ah","ha","th","at","a"]

      The letters are not case-sensitive, so this returns the same results:

      Request URL:       http://hostname:8080/words/HAT
      Expected response: ["hat","ah","ha","th","at","a"]

      Request URL:       http://hostname:8080/words/Hat
      Expected response: ["hat","ah","ha","th","at","a"]

    2. No dictionary words can be spelled with the given letters.

      An empty JSON list is returned.  For example:

      Request URL:       http://hostname:8080/words/zzz
      Expected response: []

  The Scrabble score is calculated by adding up the point values of every letter in the word.

    The point values are:

    Points | Letters
    -------+-----------------------------
       1   | A, E, I, L, N, O, R, S, T, U
       2   | D, G
       3   | B, C, M, P
       4   | F, H, V, W, Y
       5   | K
       8   | J, X
      10   | Q, Z

    For example, the following words have these scores:

    hat:  6
    code: 7
    antidisestablishmenatarianism: 39

    (From https://en.wikipedia.org/wiki/Scrabble_letter_distributions)
