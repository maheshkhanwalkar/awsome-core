# awsome-core
Improved AWS SDK for Java -- Core Supporting library

This library implements the "core" support required for the rest of the *awsome* suite of libraries.


### Purpose
The AWS SDK for Java is geared around request/response -- the libraries define methods which take in some sort of request object and return a response object.
While this makes the design of the SDK easy, the usage becomes verbose and difficult.

**Awsome** acts as a wrapper over the base AWS SDK and exposes an object-oriented approach. Instead of having service-level clients and using methods modelled
after API calls, **awsome** defines objects to represent the resources themselves and actions that can be performed on them.

For example, the following code snippet to create a bucket in S3 using the AWS SDK:
```
S3Client client = S3Client.builder().build();
CreateBucketRequest request = CreateBucketRequest.builder().bucket(name).build();
client.createBucket(request);
```

Using *awsome-s3*, this is transformed into:

```
Bucket bucket = Bucket.create(name);
```

Then, any actions to be performed on the bucket (i.e. adding or removing objects, deleting the bucket) are exposed directly as methods of the `Bucket` class.

### Features

This library (`awsome-core`) only acts as a supporting library containing code which is used by the per-service libraries. Therefore, most likely, you won't
need to reference this library directly -- it will be automatically pulled as a dependency.
