package algorithms.dp

object SequenceAlignment {
  def solve(seq1: String, seq2: String, matchScore: Int, mismatchPenalty: Int, gapPenalty: Int): (Int, String, String) = {
    val m = seq1.length
    val n = seq2.length
    val dp = Array.ofDim[Int](m + 1, n + 1)

    // Initialize DP table
    for (i <- 0 to m) dp(i)(0) = i * gapPenalty
    for (j <- 0 to n) dp(0)(j) = j * gapPenalty

    // Fill DP table
    for (i <- 1 to m) {
      for (j <- 1 to n) {
        val scoreDiag = dp(i - 1)(j - 1) + (if (seq1(i - 1) == seq2(j - 1)) matchScore else mismatchPenalty)
        val scoreLeft = dp(i)(j - 1) + gapPenalty
        val scoreUp = dp(i - 1)(j) + gapPenalty
        dp(i)(j) = List(scoreDiag, scoreLeft, scoreUp).max
      }
    }

    // Trace back to find the optimal alignment
    var alignedSeq1 = ""
    var alignedSeq2 = ""
    var i = m
    var j = n
    while (i > 0 && j > 0) {
      val score = dp(i)(j)
      if (score == dp(i - 1)(j - 1) + (if (seq1(i - 1) == seq2(j - 1)) matchScore else mismatchPenalty)) {
        alignedSeq1 = seq1(i - 1) + alignedSeq1
        alignedSeq2 = seq2(j - 1) + alignedSeq2
        i -= 1
        j -= 1
      } else if (score == dp(i)(j - 1) + gapPenalty) {
        alignedSeq1 = "-" + alignedSeq1
        alignedSeq2 = seq2(j - 1) + alignedSeq2
        j -= 1
      } else {
        alignedSeq1 = seq1(i - 1) + alignedSeq1
        alignedSeq2 = "-" + alignedSeq2
        i -= 1
      }
    }
    while (i > 0) {
      alignedSeq1 = seq1(i - 1) + alignedSeq1
      alignedSeq2 = "-" + alignedSeq2
      i -= 1
    }
    while (j > 0) {
      alignedSeq1 = "-" + alignedSeq1
      alignedSeq2 = seq2(j - 1) + alignedSeq2
      j -= 1
    }

    (dp(m)(n), alignedSeq1, alignedSeq2)
  }
}

object SequenceAlignmentExample extends App {
  val seq1 = "GATTACA"
  val seq2 = "GCATGCU"
  val matchScore = 2
  val mismatchPenalty = -1
  val gapPenalty = -2

  val (score, alignedSeq1, alignedSeq2) = SequenceAlignment.solve(seq1, seq2, matchScore, mismatchPenalty, gapPenalty)
  println(s"Optimal Alignment Score: $score")
  println(s"Aligned Sequence 1: $alignedSeq1")
  println(s"Aligned Sequence 2: $alignedSeq2")
}
