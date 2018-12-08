import React,  {Component} from 'react';
import SearchBar from './SearchBar/SearchBar';
import SearchResults from './SearchResults/SearchResults';

export default class UserView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchResults: []
        }

        this.handleSearchResultsChange = this.handleSearchResultsChange.bind(this);
    }

    handleSearchResultsChange(results) {
        this.setState({searchResults: results});
    }

    render() {
        return (
            <div>
                <SearchBar onSearch={this.handleSearchResultsChange} />
                <SearchResults searchResults={this.state.searchResults}/>
            </div>
        )
    }
}