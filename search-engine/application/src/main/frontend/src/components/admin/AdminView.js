import React,  {Component} from 'react';
import IndexedPages from './IndexedPages/IndexedPages';
import UserSearchQueries from './UserSearchQueries/UserSearchQueries';
import DatabaseInformation from './DatabaseInformation/DatabaseInformation';

export default class AdminView extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <h2>Welcome to the Admin view!</h2>
                <br />
                <IndexedPages/>
                <br />
                <UserSearchQueries/>
                <br />
                <DatabaseInformation />
            </div>
        )
    }
}