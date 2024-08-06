
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

