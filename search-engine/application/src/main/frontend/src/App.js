import React, { Component } from 'react';
import Header from './components/Header';
import UserView from './components/user/UserView';

class App extends Component {
  render() {
    return (
      <div className="container">
        <Header />
        <UserView />
      </div>
    );
  }
}

export default App;
