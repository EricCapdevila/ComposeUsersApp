
# ComposeUsersApp

The app makes a call via Retrofit to the users service api, retrieving 10 results.
The results are mapped and the repeated users are excluded, all in the view model.
The user can then filter said results using the search bar.

### Details

Architecture: MVVM.
Framwork: Compose.
Hilt: for Injecting the ViewModel with its Repository and Retrofit singleton instance.
Glide: for Image downloading.
Mockito and Junit: Very basic Unit tests for the UsersViewModel to test the users mapper and filter repeated logic.


### Missing requirements:

- More testing involving the success and error flow.
- Details page.
- Infinite scroll + paginate the service call.


