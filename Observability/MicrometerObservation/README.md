# Micrometer

## Registry

`Meter` - ilovangiz bo'yicha o'lchovlar to'plamini (biz uni alohida o'lchovlar deb ataymiz) yig'ish interface. Micrometerdagi
Meter lar yani hisoblagichlar `MeterRegistry` dan yaratiladi va unda saqlanadi. Har bir qo'llab quvvatlanadigan monitoring
tizimi `MeterRegistry` ilovasiga ega.

## Introduction

```groovy
implementation platform('io.micrometer:micrometer-bom:latest.release')
implementation 'io.micrometer:micrometer-observation'
```

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-bom</artifactId>
            <version>${micrometer.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-observation</artifactId>
    </dependency>
</dependencies>
```

Har qanday Observation yani kuzatish amalga oshiilishi uchun `ObservationHandler` objectni `ObservationRegistry` orqali
ro'yxatdan o'tkazishingiz kerak. `ObservationHandler` faqat `Observation.Context` qo'llab quvvatlanadigan ilovalarga javob
beradi va Observation(Kuzatish) ning hayotiy tsikli hodisalariga munosabat bildirish orqali timer, spans va log larni 
yaratishi mumkin.

