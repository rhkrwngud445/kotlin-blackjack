package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Player
import domain.gamer.cards.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerStateTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val playerState = Player("jack", Cards(listOf()))
        playerState.pickCard(Card(Shape.CLOVER, CardValue.JACK))
        assertThat(playerState.cards.getCards()).isEqualTo(listOf(Card(Shape.CLOVER, CardValue.JACK)))
    }

    @Test
    fun `카드 값의 합을 반환한다`() {
        val playerState =
            Player("jack", Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))
        assertThat(playerState.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘었을 경우 true를 반환한다`() {
        val playerState = Player(
            "jack",
            Card(Shape.SPADE, CardValue.JACK),
            Card(Shape.SPADE, CardValue.JACK),
            Card(Shape.SPADE, CardValue.JACK)
        )
        assertThat(playerState.checkBurst()).isTrue
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘지 않았을 경우 false를 반환한다`() {
        val playerState =
            Player("jack", Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))
        assertThat(playerState.checkBurst()).isFalse
    }
}
