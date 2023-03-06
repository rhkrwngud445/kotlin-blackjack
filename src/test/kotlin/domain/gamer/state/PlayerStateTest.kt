package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Player
import domain.gamer.cards.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerStateTest {
    @ParameterizedTest
    @MethodSource("generateCard")
    fun `뽑은 카드를 저장한다`(cards: List<Card>) {
        val playerState = Player("jack", Cards(listOf()))
        for (card in cards) {
            playerState.pickCard(card)
        }
        assertThat(playerState.cards.getCards()).isEqualTo(cards)
    }

    companion object {
        @JvmStatic
        private fun generateCard(): List<Arguments> =
            listOf(
                Arguments.of(
                    listOf(
                        Card(Shape.SPADE, CardValue.ACE),
                        Card(Shape.HEART, CardValue.TWO),
                        Card(Shape.CLOVER, CardValue.THREE)
                    ),
                    listOf(Card(Shape.SPADE, CardValue.QUEEN), Card(Shape.SPADE, CardValue.THREE))
                )
            )
    }

    @Test
    fun `카드 값의 합을 반환한다`() {
        val playerState =
            Player("jack", Cards(listOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))))
        assertThat(playerState.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘었을 경우 true를 반환한다`() {
        val playerState = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK)
                )
            )

        )
        assertThat(playerState.checkBurst()).isTrue
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘지 않았을 경우 false를 반환한다`() {
        val playerState =
            Player("jack", Cards(listOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))))
        assertThat(playerState.checkBurst()).isFalse
    }
}
