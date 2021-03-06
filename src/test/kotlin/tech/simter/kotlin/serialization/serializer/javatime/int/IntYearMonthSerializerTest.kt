package tech.simter.kotlin.serialization.serializer.javatime.int

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration.Companion.Stable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.YearMonth

/**
 * Test [IntYearMonthSerializer]
 *
 * @author RJ
 */
class IntYearMonthSerializerTest {
  private val json = Json(Stable.copy(encodeDefaults = false))

  @Serializable
  data class Bean(
    val ps: List<@Serializable(with = IntYearMonthSerializer::class) YearMonth>,
    @Serializable(with = IntYearMonthSerializer::class)
    val p1: YearMonth,
    @Serializable(with = IntYearMonthSerializer::class)
    val p2: YearMonth?,
    @Serializable(with = IntYearMonthSerializer::class)
    val p3: YearMonth? = null
  )

  @Test
  fun test() {
    val ym = YearMonth.of(2019, 1)
    val str = """{"ps":[201901],"p1":201901,"p2":null}"""
    val bean = Bean(ps = listOf(ym), p1 = ym, p2 = null)
    assertThat(json.parse(Bean.serializer(), str)).isEqualTo(bean)
    assertThat(json.stringify(Bean.serializer(), bean)).isEqualTo(str)
  }
}