package akka.http.impl.model.parser

import org.scalatest.{ FunSpec, Matchers }

class URISpec extends FunSpec with Matchers {
  describe("URI parser should") {
    it("should allow a URI with scheme and port") {
      val uri = new UriParser("http://lightbend.com").parseUriReference()
      uri.scheme shouldEqual "http"
      uri.authority.host.address shouldEqual "lightbend.com"
      uri.effectivePort shouldEqual 80
    }
    it("should allow a URI with no scheme and no port") {
      val uri = new UriParser("lightbend.com").parseUriReference()
      uri.scheme shouldBe empty
      uri.path.head shouldEqual "lightbend.com"
      uri.effectivePort shouldEqual -1
    }
    it("should allow a URI with no scheme but with port") {
      val uri = new UriParser("lightbend.com:80").parseUriReference()
      uri.scheme shouldBe empty
      uri.authority.host.address shouldEqual "lightbend.com"
      uri.effectivePort shouldEqual 80
    }
  }
}
