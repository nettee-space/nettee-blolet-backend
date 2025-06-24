package nettee.common.status

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.*
import nettee.common.status.StatusParameters.GeneralPurposeFeatures.*

class StatusCodeUtilTest: FreeSpec({
    "[GP] 다른 섹션이 모두 0일 때" - {
        "GP: NO OP" {
            val parameters = StatusParameters.generate()
                .generalPurposeFeatures()
                .systemInfoBits(0)
                .categoryBits(0)
                .instanceBits(0)

            val code = StatusCodeUtil.getAsInt(parameters)

            code shouldBe 0
        }

        val cases = listOf(
            // Single GP
            setOf(READ) to 0x40_00_00_00,
            setOf(UPDATE) to 0x20_00_00_00,
            setOf(SUBITEM_READ) to 0x08_00_00_00,
            setOf(SUBITEM_UPDATE) to 0x04_00_00_00,

            // 2 GP items
            setOf(READ, UPDATE) to 0x60_00_00_00,
            setOf(READ, SUBITEM_READ) to 0x48_00_00_00,
            setOf(READ, SUBITEM_UPDATE) to 0x44_00_00_00,
            setOf(UPDATE, SUBITEM_READ) to 0x28_00_00_00,
            setOf(UPDATE, SUBITEM_UPDATE) to 0x24_00_00_00,
            setOf(SUBITEM_READ, SUBITEM_UPDATE) to 0x0C_00_00_00,

            // 3 GP items
            setOf(READ, UPDATE, SUBITEM_READ) to 0x68_00_00_00,
            setOf(READ, UPDATE, SUBITEM_UPDATE) to 0x64_00_00_00,
            setOf(READ, SUBITEM_READ, SUBITEM_UPDATE) to 0x4C_00_00_00,
            setOf(UPDATE, SUBITEM_READ, SUBITEM_UPDATE) to 0x2C_00_00_00,
        )

        cases.forEach { (features, expectedCode) ->
            "GP: ${features.joinToString()}" {
                val parameters = StatusParameters.generate()
                    .generalPurposeFeatures(*features.toTypedArray())
                    .systemInfoBits(0)
                    .categoryBits(0)
                    .instanceBits(0)

                val code = StatusCodeUtil.getAsInt(parameters)

                code shouldBe expectedCode
            }
        }
    }

    "[SYS INFO BITS] 다른 섹션이 모두 0일 때 입력한 비트가 알맞은 위치에 대입된다." - {
        val cases = listOf(
            0x00 to 0x00_00_00_00,
            0x0F to 0x00_0F_00_00,
            0x55 to 0x00_55_00_00,
            0xFF to 0x00_FF_00_00,
        )

        cases.forEach { (input, expectedCode) ->
            "SYS INFO: $input" {
                val parameters = StatusParameters.generate()
                    .generalPurposeFeatures()
                    .systemInfoBits(input)
                    .categoryBits(0)
                    .instanceBits(0)

                val code = StatusCodeUtil.getAsInt(parameters)

                code shouldBe expectedCode
            }
        }
    }

    "[CATEGORY] 다른 섹션이 모두 0일 때 입력한 비트가 알맞은 위치에 대입된다." - {
        val cases = listOf(
            0x00 to 0x00_00_00_00,
            0x0F to 0x00_00_0F_00,
            0x55 to 0x00_00_55_00,
            0xFF to 0x00_00_FF_00,
        )

        cases.forEach { (input, expectedCode) ->
            "CATEGORY: $input" {
                val parameters = StatusParameters.generate()
                    .generalPurposeFeatures()
                    .systemInfoBits(0)
                    .categoryBits(input)
                    .instanceBits(0)

                val code = StatusCodeUtil.getAsInt(parameters)

                code shouldBe expectedCode
            }
        }
    }

    "[INSTANCE DETAIL] 다른 섹션이 모두 0일 때 입력한 비트가 알맞은 위치에 대입된다." - {
        val cases = listOf(
            0x00 to 0x00_00_00_00,
            0x0F to 0x00_00_00_0F,
            0x55 to 0x00_00_00_55,
            0xFF to 0x00_00_00_FF,
        )

        cases.forEach { (input, expectedCode) ->
            "INSTANCE DETAIL: $input" {
                val parameters = StatusParameters.generate()
                    .generalPurposeFeatures()
                    .systemInfoBits(0)
                    .categoryBits(0)
                    .instanceBits(input)

                val code = StatusCodeUtil.getAsInt(parameters)

                code shouldBe expectedCode
            }
        }
    }
})