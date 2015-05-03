/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.example.extensible.Options;
import org.example.extensible.TwoSplitOperation;
import org.example.extensible.WordSplit;
import org.example.extensible.WordSplitter;
import org.example.extensible.EnglishDictionary;
import org.example.performance.WordPair;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;

public class MyBenchmark {

    private static final EnglishDictionary ENGLISH_DICTIONARY_E = new EnglishDictionary();
    private static final org.example.performance.EnglishDictionary ENGLISH_DICTIONARY_P = new org.example.performance.EnglishDictionary();
    private static final org.example.readable.EnglishDictionary ENGLISH_DICTIONARY_R = new org.example.readable.EnglishDictionary();

    @Benchmark
    public void testExtensible() {
        final Options options = new Options(ENGLISH_DICTIONARY_E, 6, new TwoSplitOperation());
        final WordSplitter wordSplitter = new WordSplitter(options);
        final List<WordSplit> wordSplits = wordSplitter.getSplitWords();
        int counter = 1;

        if (0 < wordSplits.size()) {
            counter = 2;
        }
    }

    @Benchmark
    public void testPerformance() {
        final org.example.performance.WordSplitter wordSplitter = new org.example.performance.WordSplitter(ENGLISH_DICTIONARY_P);
        final List<WordPair> wordSplits = wordSplitter.getSplitWords();
        int counter = 1;

        if (0 < wordSplits.size()) {
            counter = 2;
        }
    }

    @Benchmark
    public void testReadable() {
        final org.example.readable.WordSplitter wordSplitter = new org.example.readable.WordSplitter(ENGLISH_DICTIONARY_R);
        final List<org.example.readable.WordPair> wordSplits = wordSplitter.getSplitWords();
        int counter = 1;

        if (0 < wordSplits.size()) {
            counter = 2;
        }
    }

}
