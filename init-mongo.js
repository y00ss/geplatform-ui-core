
db.createUser(
    {
        user: "vaadin",
        pwd: "vaadin",
        roles: [
            {
                role: "dbOwner",
                db: "geplatform_core"
            }
        ]
    }
)


db.application_user.insertMany([
    {
        username: "user",
        name: "John Doe",
        hashedPassword: "$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe",
        roles: ["USER"],
        profilePicture: null
    },
    {
        username: "admin",
        name: "Sam Wilson",
        hashedPassword: "$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.",
        roles: ["ADMIN"],
        profilePicture: null
    }
]);

db.ge_questionnaire.insertMany([
    {
        "name": "CULTURA E STRATEGIA",
        "section": "A",
        "description": "Area volta a misurare che i principi e gli obiettivi di inclusione, parita di genere e attenzione alla gender diversity dell'organizzazione siano coerenti con la sua visione, le finalita e i valori che caratterizzano l’ambiente di lavoro.",
        "weight": 15,

        "queries": [
            {
                "cluster": [
                    "MICRO",
                    "SMALL",
                    "MEDIUM",
                    "LARGE"
                ],
                "question": "Formazione e implementazione di un piano strategico come definito al punto 6.3 che possa favorire e sostenere lo sviluppo di un ambiente di lavoro inclusivo e preveda valori aziendali coerenti con una cultura inclusiva",
                "source": 20
            },
            {
                "cluster": ["MEDIUM", "LARGE"],
                "question": "Presenza di procedure interne che consentono alle risorse di esprimere, anche in modalita anonima, le proprie opinioni e dare suggerimenti per il cambiamento nell'organizzazione e favorire il dialogo e il confronto \n NOTA Sono da intendersi, oltre alle procedure in senso stretto, anche iniziative, progetti e valori stabiliti dall’azienda il cui fine sia il medesimo, ovvero consentire alle persone di esprimere opinioni e creare un ambiente aperto al confronto e al dialogo.",
                "source": 10
            },
            {
                "cluster": ["SMALL", "MEDIUM", "LARGE"],
                "question": "Presenza di attivita di comunicazione interna e di sensibilizzazione che promuovano lutilizzo di comportamenti e di un linguaggio in grado di garantire un ambiente di lavoro inclusivo e rispettoso delle diversita di genere",
                "source": 20
            },
            {
                "cluster": ["MEDIUM", "LARGE"],
                "question": "Presenza di politiche che garantiscano che i generi siano equamente rappresentati tra i relatori del panel di tavole rotonde, eveniti, convegni 0 altro evento anche di carattere scientifico",
                "source": 10
            },
            {
                "cluster": ["SMALL", "MEDIUM", "LARGE"],
                "question": "Realizzazione nell’ultimo biennio di interventi formativi a tutti i livelli,compresi i vertici, sulla differenza di genere e suo valore, gli stereotipi e gli unconscious bias",
                "source": 10
            },
            {
                "cluster": ["SMALL", "MEDIUM", "LARGE"],
                "question": "Realizzazione di interventi finalizzati all’analisi della percezione delle/dei dipendenti sulle pari opportunità nell'ultimo anno",
                "source": 20
            },
            {
                "cluster": ["MEDIUM", "LARGE"],
                "question": "Realizzazione di interventi finalizzati a promuovere le pari opportunita fuori dal proprio contesto organizzativo nell'ultimo biennio, che includano, tra altre, attivita di comunicazione e coinvolgimento dei diversi stakeholder sui temi dell’inclusione, della parita di genere e della integrazione",
                "source": 10
            }
        ]
    },
    {
        "name": "GOVERNANCE",
        "section": "B",
        "description": "Area volta a misurare il grado di maturita del modello di governance dell’organizzazione volto a definire gli adeguati presidi organizzativi e la presenza del genere di minoranza negli organi di indirizzo e controllo dell’organizzazione nonché la presenza di processi volti a identificare e porre rimedio a qualsiasi evento di non inclusione.",
        "weight": 15,
        "queries": [
            {
                "cluster": [
                    "MICRO",
                    "SMALL",
                    "MEDIUM",
                    "LARGE"
                ],
                "question": "Definizione nella governance dell'organizzazione di un presidio(comitato, unita o funzione, ruolo organizzativo, ecc.) volto alla gestione e monitoraggio delle tematiche legate all'inclusione, alla parita di genere e integrazione\nNOTA II tipo di presidio per la gestione e monitoraggio dei temi di genere può variare in relazione alle dimensioni e alla complessita dell’organizzazione. Per le organizzazioni di fascia dimensionale 1 é ipotizzabile un presidio base.",
                "source": 25
            },
            {
                "cluster": ["SMALL", "MEDIUM", "LARGE"],
                "question": "Presenza di processi per identificare, approfondire e gestire qualsiasi forma di non inclusivita",
                "source": 25
            },
            {
                "cluster": ["MEDIUM", "LARGE"],
                "question": "Presenza di un budget dell'organizzazione per lo sviluppo di attivita a supporto dell'inclusione, della parita di genere e dell’integrazione",
                "source": 15
            },
            {
                "cluster": ["MEDIUM", "LARGE"],
                "question": "Definizione di obiettivi legati alla parita di genere e loro attribuzione ai vertici e al management, per i quali saranno valutati",
                "source": 15
            },
            {
                "cluster": ["MEDIUM", "LARGE"],
                "question": "Presenza di esponenti del sesso meno rappresentato nell'organo amministrativo e di controllo della organizzazione",
                "source": 20
            }
        ]
    }
])