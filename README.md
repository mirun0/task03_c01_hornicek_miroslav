# task03_c01_hornicek_miroslav

3. Průběžná úloha: Transformace a zobrazení drátového modelu 3D tělesa

Vytvořte program pro transformaci a zobrazení drátového modelu jednoduché grafické prostorové scény složené minimálně ze tří 3D těles, alespoň jedno z každé skupiny
[DONE]  - Krychle, čtyřstěn
[DONE]  - Válec, kužel, netriviální více-boký hranol, komolý …
[DONE]  - Křivky, plochy
[DONE] Implementujte modelovací transformace posunutí, otočení a změnu měřítka pro jednotlivé objekty scény.
[DONE] Implementujte perspektivní i paralelní projekci, tzv. transformaci zobrazovacího modelu.
[DONE] Implementujte pohledovou transformaci, tzv. kameru.
[DONE] Soustřeďte se na správné ořezávání jednotlivých hran zobrazovacím objemem, stačí tzv. přísné ořezání, tj. zahazování hran, které částečně leží mimo zobrazovací objem.
[DONE] Implementujte interaktivní ovládání myší (rozhlížení) a klávesnicí (WSAD – dopředu, dozadu, vlevo, vpravo), především u definice kamery (třída Camera v transforms).
[DONE] Pro kontrolu projekce a ostatních transformací je vhodné zobrazovat osy souřadnicového systému scény jako objekt scény (tři barevné úsečky), transformovaný zvolenou projekční a pohledovou maticí, nikoli modelovací transformací.
[DONE] Doplňte předcházející program pro vykreslení hladkých křivek (Fergusonova, Bézierova a Coonsova kubika) pomocí zadaných čtyř pevných řídících bodů. Použijte definice kubik pomocí matic! Koncové body umístěte ve význačných bodech zvoleného tělesa, například v protilehlých vrcholech krychle. Ostatní body umístěte tak, aby bylo vidět zakřivení křivky v prostoru.
[] Bonus: doplňte program o zobrazení zadané parametrické plochy definované v kartézských, sférických nebo cylindrických souřadnicích. Lze využít i bikubické plochy v transforms.
[DONE] Bonus2: vytvořte animaci v čase pro některá tělesa. Může se jednat o pohyb nebo u parametrických těles o změnu tvaru plochy.