# Dummy Data

## Count

<table>
<tr>
  <th>회원 생성 초기화</th>
</tr>
<tr>
</tr>
<tr>
  <td>

- `"auth"."user"`: 2
- `"profile"."profile"`: 2
- `"blog"."blog"`: 2

  </td>
</tr>
</table>

<table>
<tr>
  <th>게시물 존재 초기화</th>
</tr>
<tr>
</tr>
<tr>
  <td>

- `"article"."draft"`: 115
- `PENDING`: 52
- `PUBLISHED`: 53
- `SUSPENDED`: 5
- `REMOVED`: 5
- `"article"."article"`: 63
  - `ACTIVE`: 53
  - `SUSPENDED`: 5
  - `REMOVED`: 5
- `"series"."series"`: 53
- `"series"."series_article"`: 110
  - Draft: 52
  - Article: 58

  </td>
</tr>
</table>

> 드래프트 블록은 아직 초기화하지 않았습니다.
<br />

## Allocation

사용자 1의 블로그에 모든 아티클, 드래프트, 시리즈가 존재합니다.

```mermaid
flowchart TD
%% ─────────────────────────────
%% 사용자/블로그별 더미데이터 개요
%% ─────────────────────────────

    subgraph U1["사용자 1 (sun)"]
        direction TB
        P1[프로필: 1개]

        subgraph B1["블로그 1 (sun)"]
            direction LR

            subgraph SG1["시리즈: 53개"]
                S1["시리즈 1"]
            end

            subgraph R1["Resources"]
                direction TB
                subgraph D1["Draft: 115개"]
                    direction TB
                    D_PEND1["Pending 52"]
                    D_PUB1["Published 53"]
                    D_SUS1["Suspended 5"]
                    D_REM1["Removed 5"]
                end
                subgraph SAG1["시리즈-아티클 연결: 110개"]
                    direction TB
                    SA1["Article 링크 58"]
                    SD1["Draft 링크 52"]
                end
                subgraph A1["Article: 63개"]
                    direction TB
                    A_ACT1["Active 53"]
                    A_SUS1["Suspended 5"]
                    A_REM1["Removed 5"]
                end
            end
        end

        P1 --> B1
        B1 --> D1
        B1 --> A1
        B1 --> S1
        S1 -->|has| SAG1
        %% D1 -.- SAG1 -.- A1
        %% D_PEND1 -.- D_PUB1 -.- D_SUS1 -.- D_REM1
        %% A_ACT1 -.- A_SUS1 -.- A_REM1
        SA1 -.- SD1
    end

%% 스타일
    classDef draft fill:#E8F4FF,stroke:#3A87F7,stroke-width:1px,color:#0A2F6B;
    classDef article fill:#E8FFE8,stroke:#3ABF7F,stroke-width:1px,color:#0A4B2F;
    classDef series fill:#F3E8FF,stroke:#9A4BFF,stroke-width:1px,color:#3B136B;
    classDef paper fill:#FFFFFF,stroke:#9A4BFF,stroke-width:1px,color:#3B136B;

    class D1,D_PEND1,D_PUB1,D_SUS1,D_REM1 draft;
    class A1,A_ACT1,A_SUS1,A_REM1 article;
    class SG1,S1,SAG1,SA1,SD1 series;
    class R1 paper;
```

```mermaid
flowchart TD
    subgraph U2["사용자 2 (moon)"]
        P2["프로필: 1개"]

        subgraph B2["블로그 2 (moon)"]
            S2["시리즈: 0개"]
            D2["Draft: 0개"]
            A2["Article: 0개"]
            SA2["시리즈-아티클 연결: 0개"]
        end

        P2 --> B2
        B2 --> D2
        B2 --> A2
        B2 --> S2
        S2 --> SA2
    end

%% 스타일
    classDef zero fill:#F5F5F5,stroke:#BFBFBF,color:#8C8C8C,stroke-dasharray: 3 2;
    class D2,A2,S2,SA2,B2,U2 zero;
```