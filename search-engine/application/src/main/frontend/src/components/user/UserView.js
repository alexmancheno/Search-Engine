import React,  {Component} from 'react';
import SearchBar from './SearchBar/SearchBar';
import SearchResults from './SearchResults/SearchResults';

export default class UserView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchResults: [],
            splicedResults: [],
            currentPage: 0,
            totalPages: 0,
        }

        this.handleSearchResultsChange = this.handleSearchResultsChange.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    handleSearchResultsChange(results) {
        this.setState({searchResults: results});
        this.setState({totalPages: Math.ceil(results.length / 10)});
        let start = this.state.currentPage * 10;
        this.setState({splicedResults: this.state.searchResults.splice(start, start + 10)});
    }

    handlePageChange(newPage) {
        console.log('EXECUTING')
        this.setState({currentPage: newPage});
        let start = this.state.currentPage * 10;
        this.setState({splicedResults: this.state.searchResults.splice(start, start + 10)});
    }

    render() {
        return (
            <div>
                <SearchBar onSearch={this.handleSearchResultsChange} />
                <SearchResults searchResults={this.state.splicedResults} totalPages={this.state.totalPages} 
                    handlePageChange={this.handlePageChange}
                />
            </div>
        )
    }
}