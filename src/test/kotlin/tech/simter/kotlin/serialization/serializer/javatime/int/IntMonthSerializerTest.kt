package tech.simter.kotlin.serialization.serializer.javatime.int

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration.Companion.Stable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Month

/**
 * Test [IntMonthSerializer]
 *
 * @author RJ
 */
class IntMonthSerializerTest {
  private val json = Json(Stable.copy(encodeDefaults = false))

  @Serializable
  data class Bean(
    val ps: List<@Serializable(with = IntMonthSerializer::class) Month>,
    @Serializable(with = IntMonthSerializer::class)
    val p1: Month,
    @Serializable(with = IntMonthSerializer::class)
    val p2: Month?,
    @Serializable(with = IntMonthSerializer::class)
    val p3: Month? = null
  )

  @Test
  fun test() {
    val month = Month.of(12)
    val str = """{"ps":[12],"p1":12,"p2":null}"""
    val bean = Bean(ps = listOf(month), p1 = month, p2 = null)
    assertThat(json.parse(Bean.serializer(), str)).isEqualTo(bean)
    assertThat(json.stringify(Bean.serializer(), bean)).isEqualTo(str)
  }
}