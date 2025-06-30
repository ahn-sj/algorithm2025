# 프로그래머스 고득점 알고리즘 Kit

## 42576_완주하지 못한 선수

| 시간 복잡도 | 자료 구조 | 소요 시간 |
|---|---|---|
| O(n) | Hash | 20m |

알고리즘 유형을 보고 문제를 푸는 방법인데 이전에는 Arrays.sort로 푼 흔적이 있었다.

유형 자체가 해시(Hash)여서 `Arrays.sort`의 내부 구현에 최적화된 방법이 있나 싶었는데 내부적으로 듀얼 피벗 퀵소트(Dual-Pivot Quicksort, `O(n log n)`)를 사용하는 듯 했다.<br/>
javadoc을 보면 다른 알고리즘 유형도 사용되는듯함

sol1. sort and loop
```java
// O(n log n) + O(n)
Arrays.sort(participant);
Arrays.sort(completion);

int i = 0;
for (i = 0; i < completion.length; i++) {
    if(!participant[i].equals(completion[i])) {
    break;
}
return participant[i];
```

sol2. hash

```java
// O(n)
Map<String, Long> race = new HashMap<>();

for (int i = 0; i < participant.length; i++) {
    race.put(participant[i], race.getOrDefault(participant[i], 0L) + 1);
}
for (int i = 0; i < completion.length; i++) {
    race.put(completion[i], race.get(completion[i]) - 1);
}

loop:
    if (entry.getValue() != 0) { // 완주하지 못한 선수
        return entry.getKey();
    }
```

---

## 1845_폰켓몬

| 시간 복잡도 | 자료 구조 | 소요 시간 |
|---|---|-------|
| O(n) | Hash | 15m   |

- 배경: 같은 종류의 폰켓몬은 같은 번호를 가지고 있습니다.
- 목표: 최대한 많은 종류의 폰켓몬을 포함해서 N/2마리를 선택하려 합니다

최댓값만 신경쓰면 되기 때문에 중복되지 않은 값을 넣는 `HashSet`을 선택함<br/>
이후 `Math.min(pokemon.size, maximum)`의 결과로 최대한 많은 종류의 폰켓몬을 찾을 수 있음

---

## 42577_전화번호 목록

| 시간 복잡도 | 자료 구조 | 소요 시간 |
|---|---|-------|
| O(n * m^2) | Hash | 35m   |

```markdown
✅ 이론적 비교
1. O(n * m²)
문자열 길이 m이 크면 매우 느려질 수 있음

예:
- n = 100,000, m = 20 → 100,000 * 400 = 40,000,000
- m = 100 → 100,000 * 10,000 = 1,000,000,000

2. O(n log n)
문자열 정렬 기반 알고리즘에서 자주 나옴
log n은 증가 속도가 매우 느림

예:
- n = 100,000 → log n ≈ 17, 전체 ≈ 1,700,000

| n   | m   | O(n \* m²)            | O(n log n)          | 더 빠른 것       |
| --- | --- | --------------------- | ------------------- | ------------ |
| 10⁵ | 10  | 10⁵ \* 100 = 10⁷      | 10⁵ \* 17 ≈ 1.7×10⁶ | ✅ O(n log n) |
| 10⁵ | 50  | 10⁵ \* 2500 = 2.5×10⁸ | 10⁵ \* 17 ≈ 1.7×10⁶ | ✅ O(n log n) |
| 10⁵ | 200 | 10⁵ \* 4×10⁴ = 4×10⁹  | 10⁵ \* 17 ≈ 1.7×10⁶ | ✅ O(n log n) |

```

- 목표: 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return

```
sol 1: return phone.startWith(next) -> 시간복잡도 O(n + n log n)
sol 2: 유형이 해시인데 해시가 어디서 쓰이지..?
```
해시를 어떻게 활용해야 하는거지..?

```java
// sol1: O(n log n)
Arrays.sort(phoneBook); // O(n log n)

for (int i = 0; i < phoneBook.length - 1; i++) { // O(n - 1)
    if (phoneBook[i + 1].startsWith(phoneBook[i])) {
        return false;
    }
}
return true;
```

```java
// sol2: O(n * m^2)
Set<String> phoneSet = new HashSet<>();

for (int i = 0; i < phoneBook.length; i++) { // O(n)
    phoneSet.add(phoneBook[i]);
}

for (int i = 0; i < phoneBook.length; i++) { // O(n * (m * m-1))
    for (int j = 0; j < phoneBook[i].length(); j++) {
        if (phoneSet.contains(phoneBook[i].substring(0, j))) {
            return false;
        }
    }
}
return true;
```

---

## 42578_의상

| 시간 복잡도 | 자료 구조 | 소요 시간 |
|---|---|-------|
| O(n * m^2) | Hash | 35m   |

옷 종류에 따라 List로 묶어야 하는건 알았는데 순회를 어떻게 해야하지?<br/>
-> 규칙을 발견해서 공식을 유도해야 하는 문제였음

조합이 되는 조건
1. 각 종류별로 최대 1가지 의상만 착용할 수 있음
2. 착용한 의상의 일부가 겹치더라도, 다른 의상이 겹치지 않거나, 혹은 의상을 추가로 더 착용한 경우에는 서로 다른 방법으로 옷을 착용한 것으로 계산
3. 하루에 최소 한 개의 의상을 입음

| 옷 종류            | 경우의 수 | 공식           |
|-----------------|-------|--------------|
| a = 1           | 1     | (a+1)-1      |
| a = 1<br/>b = 1 | 3     | (a+1)(b+1)-1 |
| a = 2<br/>b = 1 | 5     | (a+1)(b+1)-1 |

각 옷 종류에는 하나를 입거나 안입는 선택지가 포함되는데 여기에 모든 종류의 옷을 아무것도 입지 않는 경우의 수도 있어서 빼주어야 함

> **참고**<br/>
> https://velog.io/@sojungpp/JAVA프로그래머스-42578번-의상

---

