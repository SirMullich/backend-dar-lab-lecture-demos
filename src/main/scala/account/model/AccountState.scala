package account.model

object AccountState {
  // empty AccountState
  def empty() = AccountState("empty login", "empty password")
}

case class AccountState(login: String, password: String)