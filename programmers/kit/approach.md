# 프로그래머스 고득점 알고리즘 Kit

## 42576_완주하지 못한 선수

| 시간 복잡도 | 자료 구조 | 소요 시간 |
|---|---|---|
| O(n) | Hash | 20m

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

