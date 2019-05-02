package account.model

object AccountState {
  // empty AccountState
  def empty() = AccountState("", "empty password")
}

case class AccountState(email: String, password: String)