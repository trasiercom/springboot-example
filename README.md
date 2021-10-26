# springboot-example

This project shows the usage oftrasier client in a spring boot application

# Trasier noop

Trasier can be added to your project without sending any request to the server, but logging the intercepted traces instead.

In the `develop` branch of this project, the noop trasier dependency is added.
This can be useful for debugging, setting up interceptors, or performance testing purposes.

# Trasier live

By switching to the `trasier-live` branch one can send the tracing data to Trasier's backend.

There are two possibilities to do that:

## Setting up an account on trasier.com

By setting up an account on https://trasier.com one can use the high performant tracing platform to send data and see them in Trasier's UI. For details see https://docs.trasier.com/docs/index.html#_registration

## Self-hosted

It is possible to self-host Trasier's server either by implementing the trasier-servers API or by deploying the trasier-server project.
Follow instructions on https://github.com/trasiercom/trasier-server for more details.

# Example project - booking flow

- Start `springboot-offer`
- Start `springboot-payment`
- Start `springboot-booking`
- send http GET request to http://localhost:7000/offer?product=iphone
- send http POST request to http://localhost:7001/payment/{offerId}
- send http POST request to http://localhost:7002/booking?offerId={offerId}&paymentId={paymentId}

Three independent spans within one trace will be created.

NOTE: Use `default` branch for noop implementation or the `trasier-live` branch for sending data to the backend.