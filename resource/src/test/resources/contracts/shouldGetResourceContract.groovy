import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("should get resource")
    request {
        method GET()
        url("/resources/1")
    }
    response {
        status OK()
        headers {
            contentType(applicationOctetStream())
        }
    }
}