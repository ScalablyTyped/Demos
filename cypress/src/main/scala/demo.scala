import typings.cypress.global.{describe, it, cy}

@main
def main: Unit =
  describe("My First Test", (suite) => {
    it("finds the content 'type'", (ctx, done) => {
      cy.visit("https://example.cypress.io");
      cy.contains("type")
      done(())
    })
  })
end main
