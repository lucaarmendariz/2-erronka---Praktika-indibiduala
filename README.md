# 2-erronka---Praktika-indibiduala

Lehenengoz instalatu dut jspdf npm install jspdf komandoaren bidez eta gero hurrengoko kodea implementatu dut. 

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>

Gero Chart.js instalatu nuen npm install chart.js komandoaren bidez

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


Honekin jspdf eta chartjs gure proiektua garatzeko instalazioarekin amaitu dugu.

Proiektuaren erabilera:

    - Erronkaren apiaren bidez, hartzen dut Langileen get bat, honek hartzen ditu langile guztien datu guztiak.
    - Gero, api berdinarekin hartzen ditut talde bakoitzaren txanda kopurua.
    - Azkenik bilatu dut api publiko bat interneten, zenbat pokemon mota bakoitzeko dagoen ikusteko. 

Proiektua behar dituen baliabideak:
    
    - Erronkaren apia (proiektuaren karpetan dagoena E2Back deitzen dena).
    - MySQL datu base batekin funtzionatzen du. 

Proiektuaren erabilerak:

    -Erabilera oso sinplea du, bakarrik bistaratzen ditu hiru grafiko, datu ezberdinekin eta pdf bat sortzen du, pdf-an 
    aterako dira talde bakoitzaren ikasle kopurua santurtziren logoarekin eta titulu batekin, hori da guztia.